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

import br.com.codersistemas.catalogomusical.domain.Paiz;
import br.com.codersistemas.catalogomusical.repository.PaizRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/paizes")
public class PaizController {
	
	@Autowired
	private PaizRepository paizRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Paiz> listar() {
		log.debug("listar!");
		List<Paiz> findAll = paizRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paiz> buscar(@PathVariable Long id) {
		Optional<Paiz> findById = paizRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Paiz adicionar(@Valid @RequestBody Paiz entity) {
		return paizRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Paiz> excluir(@PathVariable Long id) {
		Optional<Paiz> findById = paizRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		paizRepository.delete(findById.get());
		return new ResponseEntity<Paiz>(HttpStatus.NO_CONTENT);
	}
	


}

