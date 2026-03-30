package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaPainelResponse;
import br.com.mh.cobrancas_whpp.entity.Parcela;
import br.com.mh.cobrancas_whpp.entity.ParcelaStatus;
import br.com.mh.cobrancas_whpp.repository.ParcelaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class PainelCobrancaService {

    private final ParcelaRepository parcelaRepository;
    private final MensagemCobrancaService mensagemCobrancaService;

    public PainelCobrancaService(ParcelaRepository parcelaRepository,
                                 MensagemCobrancaService mensagemCobrancaService) {
        this.parcelaRepository = parcelaRepository;
        this.mensagemCobrancaService = mensagemCobrancaService;
    }

    public List<CobrancaPainelResponse> listarCobrancasDoPainel() {
        LocalDate hoje = LocalDate.now();

        List<Parcela> parcelas = parcelaRepository.findAll().stream()
                .filter(parcela -> parcela.getStatus() != ParcelaStatus.PAGA)
                .filter(parcela -> !parcela.getDataVencimento().isAfter(hoje))
                .sorted(Comparator.comparing(Parcela::getDataVencimento))
                .toList();

        return parcelas.stream()
                .map(parcela -> montarResponse(parcela, hoje))
                .toList();
    }

    private CobrancaPainelResponse montarResponse(Parcela parcela, LocalDate hoje) {
        String status = definirStatusTela(parcela, hoje);
        String horarioSugerido = "09:00";

        String mensagem = mensagemCobrancaService.gerarMensagem(parcela);

        String textoAgenda = """
                Título: Cobrar %s - %s
                Data: %s
                Horário: %s
                Lembrete: 1 hora antes
                Descrição: Parcela de R$ %s. Telefone: %s
                """.formatted(
                parcela.getDivida().getCliente().getNome(),
                parcela.getDivida().getCliente().getLoja().getNome(),
                parcela.getDataVencimento(),
                horarioSugerido,
                parcela.getValor(),
                parcela.getDivida().getCliente().getTelefone()
        );

        return new CobrancaPainelResponse(
                parcela.getId(),
                parcela.getDivida().getCliente().getNome(),
                parcela.getDivida().getCliente().getLoja().getNome(),
                parcela.getDivida().getCliente().getTelefone(),
                parcela.getValor(),
                parcela.getDataVencimento(),
                status,
                horarioSugerido,
                mensagem,
                textoAgenda
        );
    }

    private String definirStatusTela(Parcela parcela, LocalDate hoje) {
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
}