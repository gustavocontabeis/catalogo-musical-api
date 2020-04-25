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

import br.com.codersistemas.catalogomusical.domain.Artista;
import br.com.codersistemas.catalogomusical.repository.ArtistaRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/artistas")
public class ArtistaController {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Artista> listar() {
		log.debug("listar!");
		List<Artista> findAll = artistaRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Artista> buscar(@PathVariable Long id) {
		Optional<Artista> findById = artistaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Artista adicionar(@Valid @RequestBody Artista entity) {
		return artistaRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Artista> excluir(@PathVariable Long id) {
		Optional<Artista> findById = artistaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		artistaRepository.delete(findById.get());
		return new ResponseEntity<Artista>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/paizOrigem/{id}")
	public ResponseEntity<List<Artista>> buscarPorPaizOrigem(@PathVariable("id") Long id) {
		Optional<List<Artista>> findById = artistaRepository.findByPaizOrigemId(id);
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

