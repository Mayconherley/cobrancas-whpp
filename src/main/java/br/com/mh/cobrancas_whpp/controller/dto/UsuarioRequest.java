package br.com.mh.cobrancas_whpp.controller.dto;

import br.com.mh.cobrancas_whpp.entity.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(

        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull
        PerfilUsuario perfil

) {}