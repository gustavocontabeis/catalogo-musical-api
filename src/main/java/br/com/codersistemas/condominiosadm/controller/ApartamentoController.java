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

import br.com.codersistemas.condominiosadm.domain.Apartamento;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.ApartamentoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apartamentos")
public class ApartamentoController extends BaseController<Apartamento> {
	
	@Autowired
	private ApartamentoService apartamentoService;
	
	@GetMapping
	public List<Apartamento> listar() {
		log.debug("listar!");
		List<Apartamento> findAll = apartamentoService.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}
	
	@PostMapping("/page")
	public Page<Apartamento> listar(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<Apartamento> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		Page<Apartamento> findAll = apartamentoService.findAll(specification, pageRequest);
		findAll.getContent().forEach(obj -> {
			obj.setMoradores(null);
			obj.getBloco().setApartamentos(null);
			obj.getBloco().setCondominio(null);
			obj.getBloco().setApartamentos(null);
			//ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}


	@GetMapping("/{id}")
	public ResponseEntity<Apartamento> buscar(@PathVariable Long id) {
		Optional<Apartamento> findById = apartamentoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Apartamento adicionar(@Valid @RequestBody Apartamento entity) {
		return apartamentoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Apartamento> excluir(@PathVariable Long id) {
		Optional<Apartamento> findById = apartamentoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		apartamentoService.delete(findById.get());
		return new ResponseEntity<Apartamento>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/bloco/{id}")
	public ResponseEntity<List<Apartamento>> buscarPorBloco(@PathVariable("id") Long id) {
		Optional<List<Apartamento>> findById = apartamentoService.findByBlocoId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/proprietario/{id}")
	public ResponseEntity<List<Apartamento>> buscarPorProprietario(@PathVariable("id") Long id) {
		Optional<List<Apartamento>> findById = apartamentoService.findByProprietarioId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/titular/{id}")
	public ResponseEntity<List<Apartamento>> buscarPorTitular(@PathVariable("id") Long id) {
		Optional<List<Apartamento>> findById = apartamentoService.findByTitularId(id);
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

