package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.DividaRequest;
import br.com.mh.cobrancas_whpp.entity.Divida;
import br.com.mh.cobrancas_whpp.entity.Parcela;
import br.com.mh.cobrancas_whpp.repository.ParcelaRepository;
import br.com.mh.cobrancas_whpp.service.DividaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DividaController {

    private final DividaService dividaService;
    private final ParcelaRepository parcelaRepository;

    @PostMapping("/dividas")
    @ResponseStatus(HttpStatus.CREATED)
    public Divida criar(@RequestBody @Valid DividaRequest request) {
        return dividaService.criarDivida(request);
    }

    @GetMapping("/clientes/{clienteId}/dividas")
    public List<Divida> listarPorCliente(@PathVariable Long clienteId) {
        return dividaService.listarPorCliente(clienteId);
    }

}
