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

import br.com.codersistemas.catalogomusical.domain.Instrumento;
import br.com.codersistemas.catalogomusical.repository.InstrumentoRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/instrumentos")
public class InstrumentoController {
	
	@Autowired
	private InstrumentoRepository instrumentoRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Instrumento> listar() {
		log.debug("listar!");
		List<Instrumento> findAll = instrumentoRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrumento> buscar(@PathVariable Long id) {
		Optional<Instrumento> findById = instrumentoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Instrumento adicionar(@Valid @RequestBody Instrumento entity) {
		@Valid
		Instrumento save = instrumentoRepository.save(entity);
		ReflectionUtils.mapToBasicDTO(save);
		return save;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Instrumento> excluir(@PathVariable Long id) {
		Optional<Instrumento> findById = instrumentoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		instrumentoRepository.delete(findById.get());
		return new ResponseEntity<Instrumento>(HttpStatus.NO_CONTENT);
	}
	


}

