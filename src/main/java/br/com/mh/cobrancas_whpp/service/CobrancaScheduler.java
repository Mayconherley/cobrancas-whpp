package br.com.mh.cobrancas_whpp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CobrancaScheduler {

    private final ResumoCobrancaService resumoCobrancaService;

    @Scheduled(cron = "0 0 8 * * *")
    public void executarResumoDiario() {
        LocalDate hoje = LocalDate.now();

        log.info("====================================");
        log.info("INICIANDO RESUMO AUTOMÁTICO DE COBRANÇAS");
        log.info("Data de referência: {}", hoje);

        resumoCobrancaService.gerarResumoDoDia(hoje);

        log.info("FINALIZANDO RESUMO AUTOMÁTICO DE COBRANÇAS");
        log.info("====================================");
    }
}