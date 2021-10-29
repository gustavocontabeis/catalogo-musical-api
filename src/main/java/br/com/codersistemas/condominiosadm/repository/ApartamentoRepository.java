package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Apartamento;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long>, JpaSpecificationExecutor<Apartamento> {

	Optional<List<Apartamento>> findByProprietarioId(Long id);

	Optional<List<Apartamento>> findByTitularId(Long id);

	Optional<List<Apartamento>> findByBlocoId(Long id);

	Optional<List<Apartamento>> findByBlocoCondominioId(Long id);
	
}