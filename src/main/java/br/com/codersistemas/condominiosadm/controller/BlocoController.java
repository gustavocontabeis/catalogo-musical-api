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

import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.BlocoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blocos")
public class BlocoController extends BaseController {
	
	@Autowired
	private BlocoService blocoService;
	
	@GetMapping
	public List<Bloco> listar() {
		return blocoService.findAll(Sort.by(Order.asc("nome")));
	}

	@PostMapping("/page")
	public Page<Bloco> listar(@RequestBody LazyLoadEvent event) {
		Specification<Bloco> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		return blocoService.findAll(specification, pageRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bloco> buscar(@PathVariable Long id) {
		Optional<Bloco> findById = blocoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bloco adicionar(@Valid @RequestBody Bloco entity) {
		return blocoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Bloco> excluir(@PathVariable Long id) {
		Optional<Bloco> findById = blocoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		blocoService.delete(findById.get());
		return new ResponseEntity<Bloco>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/condominio/{id}")
	public ResponseEntity<List<Bloco>> buscarPorCondominio(@PathVariable("id") Long id) {
		Optional<List<Bloco>> findById = blocoService.findByCondominioId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}
		return ResponseEntity.ok(findById.get());
	}

}
