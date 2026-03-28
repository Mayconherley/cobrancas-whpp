package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.LojaRequest;
import br.com.mh.cobrancas_whpp.entity.Loja;
import br.com.mh.cobrancas_whpp.repository.LojaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LojaService {

    private final LojaRepository lojaRepository;

    public List<Loja> listarTodas() {
        return lojaRepository.findAll();
    }

    public Loja buscarPorId(Long id) {
        return lojaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));
    }

    public Loja criar(LojaRequest request) {
        Loja loja = Loja.builder()
                .nome(request.nome())
                .nomeResponsavel(request.nomeResponsavel())
                .telefoneWhatsapp(request.telefoneWhatsapp())
                .email(request.email())
                .ativo(request.ativo())
                .receberResumoWhatsapp(request.receberResumoWhatsapp())
                .build();

        return lojaRepository.save(loja);
    }

    public Loja atualizar(Long id, LojaRequest request) {
        Loja loja = buscarPorId(id);

        loja.setNome(request.nome());
        loja.setNomeResponsavel(request.nomeResponsavel());
        loja.setTelefoneWhatsapp(request.telefoneWhatsapp());
        loja.setEmail(request.email());
        loja.setAtivo(request.ativo());
        loja.setReceberResumoWhatsapp(request.receberResumoWhatsapp());

        return lojaRepository.save(loja);
    }
}