package br.com.mh.cobrancas_whpp.controller.dto;

public record LojaResponse(
        Long id,
        String nome,
        String nomeResponsavel,
        String telefoneWhatsapp,
        String email,
        Boolean ativo,
        Boolean receberResumoWhatsapp
) {
}