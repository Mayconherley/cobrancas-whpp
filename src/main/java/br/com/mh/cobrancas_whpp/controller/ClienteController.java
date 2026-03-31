package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.ClienteRequest;
import br.com.mh.cobrancas_whpp.entity.Cliente;
import br.com.mh.cobrancas_whpp.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @GetMapping("/por-loja/{lojaId}")
    public List<Cliente> listarPorLoja(@PathVariable Long lojaId) {
        return clienteService.listarPorLoja(lojaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@RequestBody @Valid ClienteRequest request) {
        return clienteService.criar(request);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        return clienteService.atualizar(id, request);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}