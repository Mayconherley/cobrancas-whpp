package br.com.mh.cobrancas_whpp.controller;

import br.com.mh.cobrancas_whpp.controller.dto.UsuarioRequest;
import br.com.mh.cobrancas_whpp.entity.Usuario;
import br.com.mh.cobrancas_whpp.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/primeiro-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarPrimeiroAdmin(@RequestBody @Valid UsuarioRequest request) {
        return usuarioService.criarPrimeiroAdmin(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario criar(@RequestBody @Valid UsuarioRequest request) {
        return usuarioService.criarUsuario(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("usuarioLogado", authentication.getName());
        resposta.put("authorities", authentication.getAuthorities());
        return resposta;
    }
}