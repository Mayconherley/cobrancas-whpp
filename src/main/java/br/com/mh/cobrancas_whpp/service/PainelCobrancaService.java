package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaPainelResponse;
import br.com.mh.cobrancas_whpp.entity.Cliente;
import br.com.mh.cobrancas_whpp.entity.Parcela;
import br.com.mh.cobrancas_whpp.entity.ParcelaStatus;
import br.com.mh.cobrancas_whpp.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PainelCobrancaService {

    private final ParcelaRepository parcelaRepository;

    public List<CobrancaPainelResponse> listarCobrancasDoPainel() {
        LocalDate hoje = LocalDate.now();

        return parcelaRepository
                .findByStatusNotAndDataVencimentoLessThanEqual(ParcelaStatus.PAGA, hoje)
                .stream()
                .filter(parcela -> parcela.getDivida() != null && parcela.getDivida().getCliente() != null)
                .sorted(Comparator.comparing(Parcela::getDataVencimento)
                        .thenComparing(Parcela::getId))
                .map(parcela -> montarResponse(parcela, hoje))
                .toList();
    }

    private CobrancaPainelResponse montarResponse(Parcela parcela, LocalDate hoje) {
        Cliente cliente = parcela.getDivida().getCliente();

        String clienteNome = cliente.getNome();
        String lojaNome = cliente.getLoja() != null ? cliente.getLoja().getNome() : "Sem loja";
        String telefone = cliente.getTelefone();
        String statusTela = calcularStatusTela(parcela, hoje);
        String horarioSugerido = "08:00";

        String mensagem = montarMensagemCobranca(clienteNome, parcela.getValorParcela(), parcela.getDataVencimento());

        String textoAgenda = """
                Título: Cobrar %s - %s
                Data: %s
                Horário: %s
                Lembrete: 1 hora antes
                Descrição: Parcela de R$ %s. Telefone: %s
                """.formatted(
                clienteNome,
                lojaNome,
                parcela.getDataVencimento(),
                horarioSugerido,
                parcela.getValorParcela(),
                telefone
        );

        return new CobrancaPainelResponse(
                parcela.getId(),
                cliente.getId(),
                clienteNome,
                lojaNome,
                telefone,
                parcela.getValorParcela(),
                parcela.getDataVencimento(),
                statusTela,
                horarioSugerido,
                mensagem,
                textoAgenda
        );
    }

    private String calcularStatusTela(Parcela parcela, LocalDate hoje) {
        if (parcela.getStatus() == ParcelaStatus.PAGA) {
            return "PAGA";
        }

        if (parcela.getDataVencimento().isBefore(hoje)) {
            return "ATRASADA";
        }

        if (parcela.getDataVencimento().isEqual(hoje)) {
            return "VENCE_HOJE";
        }

        return "PENDENTE";
    }

    private String montarMensagemCobranca(String nomeCliente, java.math.BigDecimal valor, LocalDate dataVencimento) {
        return """
                Olá, %s.
                Passando para lembrar que há um pagamento no valor de R$ %s com vencimento em %s.
                Caso já tenha efetuado, desconsidere esta mensagem.
                """.formatted(nomeCliente, valor, dataVencimento);
    }
}