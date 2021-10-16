package br.com.codersistemas.condominiosadm.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.CondominioService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/condominios")
public class CondominioController extends BaseController<Condominio> {

	@Autowired
	private CondominioService condominioService;

	@GetMapping()
	public List<Condominio> listar() {
		List<Condominio> findAll = condominioService.findAll();
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@PostMapping("/page")
	public Page<Condominio> listar(@RequestBody LazyLoadEvent event) {

		log.info("{}", event);

		Specification<Condominio> specification = createSpecification(event);

		PageRequest pageRequest = getPageRequest(event);

		Page<Condominio> findAll = condominioService.findAll(specification, pageRequest);
		for (Condominio obj : findAll) {
		}
		findAll.getContent().forEach(obj -> {
			// obj.getSindico().getPessoa();
			// ReflectionUtils.mapToBasicDTO(obj);
			obj.setBlocos(null);
			obj.setFaturamentos(null);
			log.info("{}", obj.getNome());
		});

		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Condominio> buscar(@PathVariable Long id) {
		Optional<Condominio> findById = condominioService.findById(id);
		if (!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Condominio body = findById.get();
		body.setFaturamentos(null);
		body.setBlocos(null);
		return ResponseEntity.ok(body);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Condominio adicionar(@Valid @RequestBody Condominio entity) {
		return condominioService.save(entity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Condominio> excluir(@PathVariable Long id) {
		Optional<Condominio> findById = condominioService.findById(id);
		if (!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		condominioService.delete(findById.get());
		return new ResponseEntity<Condominio>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/sindico/{id}")
	public ResponseEntity<List<Condominio>> buscarPorSindico(@PathVariable("id") Long id) {
		Optional<List<Condominio>> findById = condominioService.findBySindicoId(id);
		if (!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		} else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

}
