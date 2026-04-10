package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.ClienteRequest;
import br.com.mh.cobrancas_whpp.controller.dto.ClienteResponse;
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
    public List<ClienteResponse> listarTodos() {
        return clienteService.listarTodos()
                .stream()
                .map(ClienteResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return ClienteResponse.fromEntity(clienteService.buscarPorId(id));
    }

    @GetMapping("/por-loja/{lojaId}")
    public List<ClienteResponse> listarPorLoja(@PathVariable Long lojaId) {
        return clienteService.listarPorLoja(lojaId)
                .stream()
                .map(ClienteResponse::fromEntity)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(@RequestBody @Valid ClienteRequest request) {
        Cliente cliente = clienteService.criar(request);
        return ClienteResponse.fromEntity(cliente);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        Cliente cliente = clienteService.atualizar(id, request);
        return ClienteResponse.fromEntity(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}