package br.com.mh.cobrancas_whpp.repository;

import br.com.mh.cobrancas_whpp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByLojaId(Long lojaId);
}