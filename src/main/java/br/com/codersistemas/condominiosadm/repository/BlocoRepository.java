package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface BlocoRepository extends JpaRepository<Bloco, Long>, JpaSpecificationExecutor<Bloco> {

	Optional<List<Bloco>> findByCondominioId(Long id);
	
}