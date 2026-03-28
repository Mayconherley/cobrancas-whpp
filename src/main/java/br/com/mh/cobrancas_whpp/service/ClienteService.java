package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.ClienteRequest;
import br.com.mh.cobrancas_whpp.entity.Cliente;
import br.com.mh.cobrancas_whpp.entity.Loja;
import br.com.mh.cobrancas_whpp.repository.ClienteRepository;
import br.com.mh.cobrancas_whpp.repository.LojaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final LojaRepository lojaRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public List<Cliente> listarPorLoja(Long lojaId) {
        return clienteRepository.findByLojaId(lojaId);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public Cliente criar(ClienteRequest request) {
        Loja loja = lojaRepository.findById(request.lojaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));

        Cliente cliente = Cliente.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .telefone(request.telefone())
                .receberNotificacaoWhatsapp(request.receberNotificacaoWhatsapp())
                .loja(loja)
                .build();

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Loja loja = lojaRepository.findById(request.lojaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));

        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());
        cliente.setTelefone(request.telefone());
        cliente.setReceberNotificacaoWhatsapp(request.receberNotificacaoWhatsapp());
        cliente.setLoja(loja);

        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }
}