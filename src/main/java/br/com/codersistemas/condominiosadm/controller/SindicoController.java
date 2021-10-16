package br.com.codersistemas.condominiosadm.controller;

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

import br.com.codersistemas.condominiosadm.domain.Sindico;
import br.com.codersistemas.condominiosadm.repository.SindicoRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sindicos")
public class SindicoController {
	
	@Autowired
	private SindicoRepository sindicoRepository;
	
	@GetMapping
	public List<Sindico> listar() {
		log.debug("listar!");
		List<Sindico> findAll = sindicoRepository.findAll(Sort.by(Order.asc("pessoa.nome"))); 
		findAll.forEach(obj -> {
			//ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sindico> buscar(@PathVariable Long id) {
		Optional<Sindico> findById = sindicoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Sindico body = findById.get();
		return ResponseEntity.ok(body);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Sindico adicionar(@Valid @RequestBody Sindico entity) {
		return sindicoRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Sindico> excluir(@PathVariable Long id) {
		Optional<Sindico> findById = sindicoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		sindicoRepository.delete(findById.get());
		return new ResponseEntity<Sindico>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<Sindico>> buscarPorPessoa(@PathVariable("id") Long id) {
		Optional<List<Sindico>> findById = sindicoRepository.findByPessoaId(id);
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

