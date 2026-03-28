package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.entity.*;
import br.com.mh.cobrancas_whpp.repository.DividaRepository;
import br.com.mh.cobrancas_whpp.repository.ParcelaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;
    private final DividaRepository dividaRepository;

    @Transactional
    public Parcela pagarParcela(Long parcelaId) {
        Parcela parcela = parcelaRepository.findById(parcelaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parcela não encontrada"));

        if (parcela.getStatus() == ParcelaStatus.PAGA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parcela já está paga");
        }

        // 1) marca a parcela como paga
        parcela.setStatus(ParcelaStatus.PAGA);
        parcelaRepository.save(parcela);

        // 2) verifica se todas as parcelas da dívida estão pagas
        Divida divida = parcela.getDivida();

        List<Parcela> parcelasDaDivida = parcelaRepository.findByDividaId(divida.getId());

        boolean todasPagas = parcelasDaDivida.stream()
                .allMatch(p -> p.getStatus() == ParcelaStatus.PAGA);

        if (todasPagas) {
            divida.setStatus(DividaStatus.QUITADA);
            dividaRepository.save(divida);
        }

        return parcela;
    }
}
