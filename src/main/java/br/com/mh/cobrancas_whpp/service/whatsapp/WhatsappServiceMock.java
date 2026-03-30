package br.com.mh.cobrancas_whpp.service.whatsapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WhatsappServiceMock implements WhatsappService {

    public void enviarMensagem(String telefoneDestino, String mensagem) {
        log.info("====================================");
        log.info("SIMULAÇÃO DE ENVIO WHATSAPP");
        log.info("Destino: {}", telefoneDestino);
        log.info("Mensagem: {}", mensagem);
        log.info("====================================");
    }
}