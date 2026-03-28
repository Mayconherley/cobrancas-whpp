package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.entity.Parcela;
import br.com.mh.cobrancas_whpp.repository.ParcelaRepository;
import br.com.mh.cobrancas_whpp.service.ParcelaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcelas")
@RequiredArgsConstructor
public class ParcelaController {

    private final ParcelaRepository parcelaRepository;
    private final ParcelaService parcelaService;

    @GetMapping("/por-divida/{dividaId}")
    public List<Parcela> listarPorDivida(@PathVariable Long dividaId) {
        return parcelaRepository.findByDividaId(dividaId);
    }

    @PatchMapping("/{parcelaId}/pagar")
    @ResponseStatus(HttpStatus.OK)
    public Parcela pagar(@PathVariable Long parcelaId) {
        return parcelaService.pagarParcela(parcelaId);
    }
}
