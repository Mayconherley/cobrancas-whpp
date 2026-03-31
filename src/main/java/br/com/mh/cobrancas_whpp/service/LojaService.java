package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.LojaRequest;
import br.com.mh.cobrancas_whpp.controller.dto.LojaResponse;
import br.com.mh.cobrancas_whpp.entity.Loja;
import br.com.mh.cobrancas_whpp.repository.LojaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LojaService {

    private final LojaRepository lojaRepository;

    public List<LojaResponse> listarTodas() {
        return lojaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public LojaResponse buscarPorId(Long id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));

        return toResponse(loja);
    }

    public LojaResponse criar(LojaRequest request) {
        Loja loja = Loja.builder()
                .nome(request.nome())
                .nomeResponsavel(request.nomeResponsavel())
                .telefoneWhatsapp(request.telefoneWhatsapp())
                .email(request.email())
                .ativo(request.ativo())
                .receberResumoWhatsapp(request.receberResumoWhatsapp())
                .build();

        Loja salva = lojaRepository.save(loja);
        return toResponse(salva);
    }

    public LojaResponse atualizar(Long id, LojaRequest request) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));

        loja.setNome(request.nome());
        loja.setNomeResponsavel(request.nomeResponsavel());
        loja.setTelefoneWhatsapp(request.telefoneWhatsapp());
        loja.setEmail(request.email());
        loja.setAtivo(request.ativo());
        loja.setReceberResumoWhatsapp(request.receberResumoWhatsapp());

        Loja atualizada = lojaRepository.save(loja);
        return toResponse(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));
        lojaRepository.delete(loja);
    }

    private LojaResponse toResponse(Loja loja) {
        return new LojaResponse(
                loja.getId(),
                loja.getNome(),
                loja.getNomeResponsavel(),
                loja.getTelefoneWhatsapp(),
                loja.getEmail(),
                loja.getAtivo(),
                loja.getReceberResumoWhatsapp()
        );
    }
}