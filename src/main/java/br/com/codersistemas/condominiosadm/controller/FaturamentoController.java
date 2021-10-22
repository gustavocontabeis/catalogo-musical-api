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

import br.com.codersistemas.condominiosadm.domain.Faturamento;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.FaturamentoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faturamentos")
public class FaturamentoController extends BaseController<Faturamento> {
	
	@Autowired
	private FaturamentoService faturamentoService;
	
	@GetMapping
	public List<Faturamento> listar() {
		return faturamentoService.findAll(Sort.by(Order.asc("id")));
	}
	
	@PostMapping("/page")
	public Page<Faturamento> listar(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<Faturamento> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		return faturamentoService.findAll(specification, pageRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Faturamento> buscar(@PathVariable Long id) {
		Optional<Faturamento> findById = faturamentoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Faturamento adicionar(@Valid @RequestBody Faturamento entity) {
		return faturamentoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Faturamento> excluir(@PathVariable Long id) {
		Optional<Faturamento> findById = faturamentoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		faturamentoService.delete(findById.get());
		return new ResponseEntity<Faturamento>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/condominio/{id}")
	public ResponseEntity<List<Faturamento>> buscarPorCondominio(@PathVariable("id") Long id) {
		Optional<List<Faturamento>> findById = faturamentoService.findByCondominioId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}
		return ResponseEntity.ok(findById.get());
	}

}

