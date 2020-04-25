package br.com.codersistemas.catalogomusical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codersistemas.catalogomusical.domain.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	Optional<List<Artista>> findByPaizOrigemId(Long id);
}
