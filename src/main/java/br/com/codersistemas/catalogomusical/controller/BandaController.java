package br.com.codersistemas.catalogomusical.controller;

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

import br.com.codersistemas.catalogomusical.domain.Banda;
import br.com.codersistemas.catalogomusical.repository.BandaRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bandas")
public class BandaController {
	
	@Autowired
	private BandaRepository bandaRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Banda> listar() {
		log.info("listar!");
		List<Banda> findAll = bandaRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Banda> buscar(@PathVariable Long id) {
		Optional<Banda> findById = bandaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			ReflectionUtils.mapToBasicDTO(findById);
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Banda adicionar(@Valid @RequestBody Banda entity) {
		return bandaRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Banda> excluir(@PathVariable Long id) {
		Optional<Banda> findById = bandaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bandaRepository.delete(findById.get());
		return new ResponseEntity<Banda>(HttpStatus.NO_CONTENT);
	}

}

