package br.com.codersistemas.condominiosadm.controller;

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

import br.com.codersistemas.condominiosadm.domain.CentroDeCusto;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.CentroDeCustoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/centros-de-custos")
public class CentroDeCustoController extends BaseController<CentroDeCusto> {
	
	@Autowired
	private CentroDeCustoService centroDeCustoService;
	
	@GetMapping
	public List<CentroDeCusto> listar() {
		log.debug("listar!");
		List<CentroDeCusto> findAll = centroDeCustoService.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@PostMapping("/page")
	public Page<CentroDeCusto> listar(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<CentroDeCusto> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		Page<CentroDeCusto> findAll = centroDeCustoService.findAll(specification, pageRequest);
		findAll.getContent().forEach(obj -> {
			// ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CentroDeCusto> buscar(@PathVariable Long id) {
		Optional<CentroDeCusto> findById = centroDeCustoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CentroDeCusto adicionar(@Valid @RequestBody CentroDeCusto entity) {
		return centroDeCustoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CentroDeCusto> excluir(@PathVariable Long id) {
		Optional<CentroDeCusto> findById = centroDeCustoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		centroDeCustoService.delete(findById.get());
		return new ResponseEntity<CentroDeCusto>(HttpStatus.NO_CONTENT);
	}

}

