package br.com.mh.cobrancas_whpp.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividaRequest(

        @NotNull
        Long clienteId,

        @NotNull
        BigDecimal valorPrincipal,

        /**
         * Percentual total de juros sobre a dívida.
         * Exemplo: 20 = 20% sobre o valor principal.
         */
        @NotNull
        BigDecimal jurosPercentual,

        @NotNull
        @Min(1)
        Integer numeroParcelas,

        @NotNull
        LocalDate dataPrimeiroVencimento

) {}
