package br.com.mh.cobrancas_whpp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "whatsapp.api")
public record WhatsappProperties(
        String baseUrl,
        String version,
        String phoneNumberId,
        String token,
        String templateName,
        String languageCode
) {}