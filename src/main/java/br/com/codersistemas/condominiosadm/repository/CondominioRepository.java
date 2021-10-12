package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface CondominioRepository extends JpaRepository<Condominio, Long>, JpaSpecificationExecutor<Condominio>{

	Optional<List<Condominio>> findBySindicoId(Long id);
	
}

