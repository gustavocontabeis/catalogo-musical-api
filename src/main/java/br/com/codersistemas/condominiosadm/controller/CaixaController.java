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

import br.com.codersistemas.condominiosadm.domain.Caixa;
import br.com.codersistemas.condominiosadm.repository.CaixaRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/caixas")
public class CaixaController {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	//declaracoes
	
	@GetMapping
	public List<Caixa> listar() {
		log.debug("listar!");
		List<Caixa> findAll = caixaRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Caixa> buscar(@PathVariable Long id) {
		Optional<Caixa> findById = caixaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Caixa adicionar(@Valid @RequestBody Caixa entity) {
		return caixaRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Caixa> excluir(@PathVariable Long id) {
		Optional<Caixa> findById = caixaRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		caixaRepository.delete(findById.get());
		return new ResponseEntity<Caixa>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/condominio/{id}")
	public ResponseEntity<List<Caixa>> buscarPorCondominio(@PathVariable("id") Long id) {
		Optional<List<Caixa>> findById = caixaRepository.findByCondominioId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<Caixa>> buscarPorPessoa(@PathVariable("id") Long id) {
		Optional<List<Caixa>> findById = caixaRepository.findByPessoaId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/centroDeCusto/{id}")
	public ResponseEntity<List<Caixa>> buscarPorCentroDeCusto(@PathVariable("id") Long id) {
		Optional<List<Caixa>> findById = caixaRepository.findByCentroDeCustoId(id);
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
