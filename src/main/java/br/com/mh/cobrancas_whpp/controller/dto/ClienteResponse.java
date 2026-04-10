package br.com.mh.cobrancas_whpp.controller.dto;

import br.com.mh.cobrancas_whpp.entity.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String cpf,
        String telefone,
        Boolean receberNotificacaoWhatsapp,
        Long lojaId,
        String nomeLoja
) {
    public static ClienteResponse fromEntity(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getReceberNotificacaoWhatsapp(),
                cliente.getLoja().getId(),
                cliente.getLoja().getNome()
        );
    }
}