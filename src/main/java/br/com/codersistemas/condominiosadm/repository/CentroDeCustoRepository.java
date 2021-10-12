package br.com.codersistemas.condominiosadm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.codersistemas.condominiosadm.domain.CentroDeCusto;
import br.com.codersistemas.condominiosadm.domain.Condominio;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long>, JpaSpecificationExecutor<CentroDeCusto> {

	
}

