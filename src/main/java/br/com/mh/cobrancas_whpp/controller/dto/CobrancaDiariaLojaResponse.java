package br.com.mh.cobrancas_whpp.controller.dto;

import java.time.LocalDate;
import java.util.List;

public record CobrancaDiariaLojaResponse(
        Long lojaId,
        String nomeLoja,
        String telefoneWhatsappLoja,
        Boolean receberResumoWhatsapp,
        LocalDate dataReferencia,
        String mensagemResumoLoja,
        List<CobrancaClienteResumoResponse> cobrancasDoDia
) {}