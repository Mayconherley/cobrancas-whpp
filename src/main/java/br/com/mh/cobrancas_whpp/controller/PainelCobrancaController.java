package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaPainelResponse;
import br.com.mh.cobrancas_whpp.service.PainelCobrancaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/painel/cobrancas")
public class PainelCobrancaController {

    private final PainelCobrancaService painelCobrancaService;

    public PainelCobrancaController(PainelCobrancaService painelCobrancaService) {
        this.painelCobrancaService = painelCobrancaService;
    }

    @GetMapping
    public List<CobrancaPainelResponse> listar() {
        return painelCobrancaService.listarCobrancasDoPainel();
    }
}