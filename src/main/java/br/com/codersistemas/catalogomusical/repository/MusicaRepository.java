package br.com.codersistemas.catalogomusical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codersistemas.catalogomusical.domain.Banda;
import br.com.codersistemas.catalogomusical.domain.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
	
	Optional<List<Musica>> findByAlbumId(Long id);
	
}
