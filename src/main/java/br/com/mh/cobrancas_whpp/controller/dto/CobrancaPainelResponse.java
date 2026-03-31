package br.com.mh.cobrancas_whpp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CobrancaPainelResponse(
        Long parcelaId,
        Long clienteId,
        String clienteNome,
        String lojaNome,
        String telefone,
        BigDecimal valor,
        LocalDate dataVencimento,
        String status,
        String horarioSugerido,
        String mensagemCobranca,
        String textoAgenda
) {
}