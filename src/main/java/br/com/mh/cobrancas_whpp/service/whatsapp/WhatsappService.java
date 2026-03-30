package br.com.mh.cobrancas_whpp.service.whatsapp;

public interface WhatsappService {
    void enviarMensagem(String telefoneDestino, String mensagem);

}