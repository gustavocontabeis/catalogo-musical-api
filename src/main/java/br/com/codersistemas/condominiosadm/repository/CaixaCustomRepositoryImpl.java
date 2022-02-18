package br.com.codersistemas.condominiosadm.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.codersistemas.condominiosadm.domain.Caixa;

public class CaixaCustomRepositoryImpl implements CaixaCustomRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<List<Caixa>> findLastByCondominio(Long idCondominio) {
		Query query = em.createQuery("select obj from Caixa obj where obj.condominio.id = :condominio order by obj.id desc");
		query.setMaxResults(1);
		query.setParameter("condominio", idCondominio);
		return Optional.of(query.getResultList());
	}
}
