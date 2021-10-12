package br.com.codersistemas.condominiosadm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {
	
}
