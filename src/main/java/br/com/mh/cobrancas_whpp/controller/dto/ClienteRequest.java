package br.com.mh.cobrancas_whpp.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String telefone,

        @NotNull
        Boolean receberNotificacaoWhatsapp,

        @NotNull
        Long lojaId

) {}