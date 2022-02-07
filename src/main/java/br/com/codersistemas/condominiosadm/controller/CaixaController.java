package br.com.codersistemas.condominiosadm.controller;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
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
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.CaixaService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/caixas")
public class CaixaController extends BaseController<Caixa> {
	
	@Autowired
	private CaixaService caixaService;
	
	//declaracoes
	
	@GetMapping
	public List<Caixa> listar() {
		log.debug("listar!");
		return caixaService.findAll(Sort.by(Order.asc("id")));
	}
	
	@PostMapping("/page")
	public Page<Caixa> listar(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<Caixa> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		return caixaService.findAll(specification, pageRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Caixa> buscar(@PathVariable Long id) {
		Optional<Caixa> findById = caixaService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Caixa adicionar(@Valid @RequestBody Caixa entity) {
		return caixaService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Caixa> excluir(@PathVariable Long id) {
		Optional<Caixa> findById = caixaService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		caixaService.delete(findById.get());
		return new ResponseEntity<Caixa>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/condominio/{id}")
	public ResponseEntity<List<Caixa>> buscarPorCondominio(@PathVariable("id") Long id) {
		Optional<List<Caixa>> findById = caixaService.findByCondominioId(id);
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
		Optional<List<Caixa>> findById = caixaService.findByPessoaId(id);
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
		Optional<List<Caixa>> findById = caixaService.findByCentroDeCustoId(id);
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

