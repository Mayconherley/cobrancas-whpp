package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.LojaRequest;
import br.com.mh.cobrancas_whpp.entity.Loja;
import br.com.mh.cobrancas_whpp.service.LojaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lojas")
@RequiredArgsConstructor
public class LojaController {

    private final LojaService lojaService;

    @GetMapping
    public List<Loja> listarTodas() {
        return lojaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Loja buscarPorId(@PathVariable Long id) {
        return lojaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loja criar(@RequestBody @Valid LojaRequest request) {
        return lojaService.criar(request);
    }

    @PutMapping("/{id}")
    public Loja atualizar(@PathVariable Long id, @RequestBody @Valid LojaRequest request) {
        return lojaService.atualizar(id, request);
    }
}