package br.com.mh.cobrancas_whpp;

import br.com.mh.cobrancas_whpp.config.WhatsappProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WhatsappProperties.class)
public class CobrancasWhppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobrancasWhppApplication.class, args);
    }
}