package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long>, JpaSpecificationExecutor<Morador> {

	Optional<List<Morador>> findByPessoaId(Long id);

	Optional<List<Morador>> findByApartamentoId(Long id);
	
}