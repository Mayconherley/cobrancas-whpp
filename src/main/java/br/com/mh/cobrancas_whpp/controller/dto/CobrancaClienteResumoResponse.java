package br.com.mh.cobrancas_whpp.controller.dto;

import java.math.BigDecimal;

public record CobrancaClienteResumoResponse(
        Long clienteId,
        String nomeCliente,
        String telefoneCliente,
        BigDecimal valorParcela,
        String mensagemPronta
) {}