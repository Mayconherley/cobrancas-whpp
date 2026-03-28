package br.com.mh.cobrancas_whpp.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LojaRequest(

        @NotBlank
        String nome,

        @NotBlank
        String nomeResponsavel,

        @NotBlank
        String telefoneWhatsapp,

        @Email
        String email,

        @NotNull
        Boolean ativo,

        @NotNull
        Boolean receberResumoWhatsapp

) {}