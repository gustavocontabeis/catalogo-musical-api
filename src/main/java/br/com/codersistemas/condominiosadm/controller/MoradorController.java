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

import br.com.codersistemas.condominiosadm.domain.Morador;
import br.com.codersistemas.condominiosadm.repository.MoradorRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/moradores")
public class MoradorController {
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Morador> listar() {
		log.debug("listar!");
		List<Morador> findAll = moradorRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Morador> buscar(@PathVariable Long id) {
		Optional<Morador> findById = moradorRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Morador adicionar(@Valid @RequestBody Morador entity) {
		return moradorRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Morador> excluir(@PathVariable Long id) {
		Optional<Morador> findById = moradorRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		moradorRepository.delete(findById.get());
		return new ResponseEntity<Morador>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<Morador>> buscarPorPessoa(@PathVariable("id") Long id) {
		Optional<List<Morador>> findById = moradorRepository.findByPessoaId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/apartamento/{id}")
	public ResponseEntity<List<Morador>> buscarPorApartamento(@PathVariable("id") Long id) {
		Optional<List<Morador>> findById = moradorRepository.findByApartamentoId(id);
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
