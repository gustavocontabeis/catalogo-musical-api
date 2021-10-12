package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Banco;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface BancoRepository extends JpaRepository<Banco, Long>, JpaSpecificationExecutor<Banco> {

	Optional<List<Banco>> findByCondominioId(Long id);

	
}
