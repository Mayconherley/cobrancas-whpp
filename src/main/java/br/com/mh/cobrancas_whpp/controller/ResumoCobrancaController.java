package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaDiariaLojaResponse;
import br.com.mh.cobrancas_whpp.service.ResumoCobrancaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/resumo-cobrancas")
@RequiredArgsConstructor
public class ResumoCobrancaController {

    private final ResumoCobrancaService resumoCobrancaService;

    @GetMapping("/dia")
    public List<CobrancaDiariaLojaResponse> buscarResumoDoDia(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate data
    ) {
        LocalDate dataReferencia = (data != null) ? data : LocalDate.now();
        return resumoCobrancaService.gerarResumoDoDia(dataReferencia);
    }
}