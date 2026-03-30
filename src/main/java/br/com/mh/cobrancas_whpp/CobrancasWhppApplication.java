package br.com.mh.cobrancas_whpp;

import br.com.mh.cobrancas_whpp.config.WhatsappProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(WhatsappProperties.class)
@EnableScheduling
public class CobrancasWhppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobrancasWhppApplication.class, args);
    }

}