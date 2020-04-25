package br.com.codersistemas.catalogomusical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codersistemas.catalogomusical.domain.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

	Optional<List<Album>> findByBandaId(Long id);
	
}

