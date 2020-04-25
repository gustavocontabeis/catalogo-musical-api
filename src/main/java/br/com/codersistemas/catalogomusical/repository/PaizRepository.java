package br.com.codersistemas.catalogomusical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codersistemas.catalogomusical.domain.Paiz;

@Repository
public interface PaizRepository extends JpaRepository<Paiz, Long> {
	
}

