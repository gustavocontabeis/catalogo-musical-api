package br.com.codersistemas.condominiosadm.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Sindico;

public interface SindicoRepository extends JpaRepository<Sindico, Long>, JpaSpecificationExecutor<Sindico> {

	Optional<List<Sindico>> findByPessoaId(Long id);
	
}
