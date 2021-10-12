package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Boleto;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface BoletoRepository extends JpaRepository<Boleto, Long>, JpaSpecificationExecutor<Boleto> {

	Optional<List<Boleto>> findByApartamentoId(Long id);

	Optional<List<Boleto>> findByFaturamentoId(Long id);

	Optional<List<Boleto>> findByTitularId(Long id);
	
}

