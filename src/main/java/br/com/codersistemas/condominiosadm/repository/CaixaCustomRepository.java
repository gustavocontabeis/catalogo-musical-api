package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import br.com.codersistemas.condominiosadm.domain.Caixa;

public interface CaixaCustomRepository {
	
	public Optional<List<Caixa>> findLastByCondominio(Long idCondominio);
}
