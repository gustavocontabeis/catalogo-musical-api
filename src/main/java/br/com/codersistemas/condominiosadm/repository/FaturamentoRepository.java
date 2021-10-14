package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Faturamento;

public interface FaturamentoRepository extends JpaRepository<Faturamento, Long>, JpaSpecificationExecutor<Faturamento> {

	Optional<List<Faturamento>> findByCondominioId(Long id);

}
