package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaClienteResumoResponse;
import br.com.mh.cobrancas_whpp.controller.dto.CobrancaDiariaLojaResponse;
import br.com.mh.cobrancas_whpp.entity.Cliente;
import br.com.mh.cobrancas_whpp.entity.Loja;
import br.com.mh.cobrancas_whpp.entity.Parcela;
import br.com.mh.cobrancas_whpp.entity.ParcelaStatus;
import br.com.mh.cobrancas_whpp.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumoCobrancaService {

    private final ParcelaRepository parcelaRepository;

    public List<CobrancaDiariaLojaResponse> gerarResumoDoDia(LocalDate dataReferencia) {
        List<Parcela> parcelasDoDia = parcelaRepository.findByDataVencimentoAndStatus(
                dataReferencia,
                ParcelaStatus.PENDENTE
        );

        Map<Loja, List<Parcela>> parcelasAgrupadasPorLoja = parcelasDoDia.stream()
                .filter(parcela -> parcela.getDivida().getCliente().getLoja().getAtivo())
                .collect(Collectors.groupingBy(parcela -> parcela.getDivida().getCliente().getLoja()));

        List<CobrancaDiariaLojaResponse> resposta = new ArrayList<>();

        for (Map.Entry<Loja, List<Parcela>> entry : parcelasAgrupadasPorLoja.entrySet()) {
            Loja loja = entry.getKey();
            List<Parcela> parcelasDaLoja = entry.getValue();

            List<CobrancaClienteResumoResponse> cobrancasClientes = parcelasDaLoja.stream()
                    .map(parcela -> {
                        Cliente cliente = parcela.getDivida().getCliente();

                        String mensagem = "Olá, " + cliente.getNome()
                                + ". Passando para lembrar que hoje é a data do seu pagamento. "
                                + "Caso já tenha efetuado, desconsidere esta mensagem.";

                        return new CobrancaClienteResumoResponse(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getTelefone(),
                                parcela.getValorParcela(),
                                mensagem
                        );
                    })
                    .toList();

            String nomesClientes = cobrancasClientes.stream()
                    .map(CobrancaClienteResumoResponse::nomeCliente)
                    .distinct()
                    .collect(Collectors.joining(", "));

            String mensagemResumo = "Olá, " + loja.getNomeResponsavel()
                    + ", da loja " + loja.getNome()
                    + ". Hoje é dia de pagamento de: " + nomesClientes + ".";

            resposta.add(new CobrancaDiariaLojaResponse(
                    loja.getId(),
                    loja.getNome(),
                    loja.getTelefoneWhatsapp(),
                    loja.getReceberResumoWhatsapp(),
                    dataReferencia,
                    mensagemResumo,
                    cobrancasClientes
            ));
        }

        return resposta;
    }
}