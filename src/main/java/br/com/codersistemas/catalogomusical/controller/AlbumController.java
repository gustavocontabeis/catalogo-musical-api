package br.com.codersistemas.catalogomusical.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codersistemas.catalogomusical.domain.Album;
import br.com.codersistemas.catalogomusical.domain.Banda;
import br.com.codersistemas.catalogomusical.repository.AlbumRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/albums")
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Album> listar() {
		log.debug("listar!");
		List<Album> findAll = albumRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Album> buscar(@PathVariable Long id) {
		Optional<Album> findById = albumRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Album adicionar(@RequestBody Album entity) {
		return albumRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Album> excluir(@PathVariable Long id) {
		Optional<Album> findById = albumRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		albumRepository.delete(findById.get());
		return new ResponseEntity<Album>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/banda/{id}")
	public ResponseEntity<List<Album>> buscarPorBanda(@PathVariable("id") Long id) {
		Optional<List<Album>> findById = albumRepository.findByBandaId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}
	
}

