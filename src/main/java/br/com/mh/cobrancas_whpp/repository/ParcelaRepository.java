package br.com.mh.cobrancas_whpp.repository;

import br.com.mh.cobrancas_whpp.entity.Parcela;
import br.com.mh.cobrancas_whpp.entity.ParcelaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    List<Parcela> findByDividaId(Long dividaId);

    List<Parcela> findByDataVencimento(LocalDate dataVencimento);

    List<Parcela> findByDataVencimentoAndStatus(LocalDate dataVencimento, ParcelaStatus status);
}