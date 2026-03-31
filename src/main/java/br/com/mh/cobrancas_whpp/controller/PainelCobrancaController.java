package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaPainelResponse;
import br.com.mh.cobrancas_whpp.service.PainelCobrancaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/painel/cobrancas")
@RequiredArgsConstructor
public class PainelCobrancaController {

    private final PainelCobrancaService painelCobrancaService;

    @GetMapping
    public List<CobrancaPainelResponse> listar() {
        return painelCobrancaService.listarCobrancasDoPainel();
    }
}