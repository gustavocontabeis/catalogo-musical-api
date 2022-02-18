package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.codersistemas.condominiosadm.domain.Caixa;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface CaixaRepository extends JpaRepository<Caixa, Long>, JpaSpecificationExecutor<Caixa>, CaixaCustomRepository {

	@Query(value = "from Caixa cx where cx.condominio = :condominio")
	public Caixa findLast(@Param(value = "condominio") Condominio condominio);

	public Optional<List<Caixa>> findByCentroDeCustoId(Long id);

	public Optional<List<Caixa>> findByCondominioId(Long id);

	public Optional<List<Caixa>> findByPessoaId(Long id);

}
