package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.service.EnvioResumoWhatsappService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/whatsapp")
@RequiredArgsConstructor
public class WhatsappResumoController {

    private final EnvioResumoWhatsappService envioResumoWhatsappService;

    @PostMapping("/resumo-dia")
    public Map<String, String> enviarResumoDoDia(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate data
    ) {
        LocalDate dataReferencia = (data != null) ? data : LocalDate.now();
        envioResumoWhatsappService.enviarResumoDoDia(dataReferencia);

        return Map.of("mensagem", "Resumo processado com sucesso");
    }
}