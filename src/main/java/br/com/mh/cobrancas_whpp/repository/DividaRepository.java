package br.com.mh.cobrancas_whpp.repository;

import br.com.mh.cobrancas_whpp.entity.Cliente;
import br.com.mh.cobrancas_whpp.entity.Divida;
import br.com.mh.cobrancas_whpp.entity.DividaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DividaRepository extends JpaRepository<Divida, Long> {

    List<Divida> findByCliente(Cliente cliente);

    List<Divida> findByStatus(DividaStatus status);
}
