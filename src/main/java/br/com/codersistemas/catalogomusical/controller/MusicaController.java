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

import br.com.codersistemas.catalogomusical.domain.Musica;
import br.com.codersistemas.catalogomusical.repository.MusicaRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/musicas")
public class MusicaController {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Musica> listar() {
		log.debug("listar!");
		List<Musica> findAll = musicaRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Musica> buscar(@PathVariable Long id) {
		Optional<Musica> findById = musicaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Musica adicionar(@Valid @RequestBody Musica entity) {
		return musicaRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Musica> excluir(@PathVariable Long id) {
		Optional<Musica> findById = musicaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		musicaRepository.delete(findById.get());
		return new ResponseEntity<Musica>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/album/{id}")
	public ResponseEntity<List<Musica>> buscarPorAlbum(@PathVariable("id") Long id) {
		Optional<List<Musica>> findById = musicaRepository.findByAlbumId(id);
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

