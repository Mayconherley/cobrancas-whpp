package br.com.mh.cobrancas_whpp.repository;



import br.com.mh.cobrancas_whpp.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long> {

    boolean existsByTelefoneWhatsapp(String telefoneWhatsapp);
}

