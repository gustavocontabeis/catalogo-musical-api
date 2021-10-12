package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.BancoLancamento;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface BancoLancamentoRepository extends JpaRepository<BancoLancamento, Long>, JpaSpecificationExecutor<BancoLancamento> {

	Optional<List<BancoLancamento>> findByBancoId(Long id);

	Optional<List<BancoLancamento>> findByCentroDeCustoId(Long id);
	
}
