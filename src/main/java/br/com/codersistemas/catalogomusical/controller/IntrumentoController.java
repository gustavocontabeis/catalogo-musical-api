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

import br.com.codersistemas.catalogomusical.domain.Intrumento;
import br.com.codersistemas.catalogomusical.repository.IntrumentoRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/intrumentos")
public class IntrumentoController {
	
	@Autowired
	private IntrumentoRepository intrumentoRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Intrumento> listar() {
		log.debug("listar!");
		List<Intrumento> findAll = intrumentoRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Intrumento> buscar(@PathVariable Long id) {
		Optional<Intrumento> findById = intrumentoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Intrumento adicionar(@Valid @RequestBody Intrumento entity) {
		return intrumentoRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Intrumento> excluir(@PathVariable Long id) {
		Optional<Intrumento> findById = intrumentoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		intrumentoRepository.delete(findById.get());
		return new ResponseEntity<Intrumento>(HttpStatus.NO_CONTENT);
	}
	


}

