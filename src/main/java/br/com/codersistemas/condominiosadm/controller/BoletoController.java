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

import br.com.codersistemas.condominiosadm.domain.Boleto;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.BoletoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/boletos")
public class BoletoController extends BaseController<Boleto> {
	
	@Autowired
	private BoletoService boletoService;
	
	//declaracoes
	
	@GetMapping
	public List<Boleto> listar() {
		log.debug("listar!");
		List<Boleto> findAll = boletoService.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}
	
	@PostMapping("/page")
	public Page<Boleto> listar(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<Boleto> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		Page<Boleto> findAll = boletoService.findAll(specification, pageRequest);
		findAll.getContent().forEach(obj -> {
			obj.getApartamento().setBloco(null);
			obj.getApartamento().setMoradores(null);
			obj.getApartamento().setProprietario(null);
			obj.getApartamento().setTitular(null);
			obj.getFaturamento().setBoletos(null);
			obj.getFaturamento().getCondominio().setBlocos(null);
			obj.getFaturamento().getCondominio().setFaturamentos(null);
			obj.getFaturamento().getCondominio().setSindico(null);
			// ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Boleto> buscar(@PathVariable Long id) {
		Optional<Boleto> findById = boletoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Boleto adicionar(@Valid @RequestBody Boleto entity) {
		return boletoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boleto> excluir(@PathVariable Long id) {
		Optional<Boleto> findById = boletoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		boletoService.delete(findById.get());
		return new ResponseEntity<Boleto>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/apartamento/{id}")
	public ResponseEntity<List<Boleto>> buscarPorApartamento(@PathVariable("id") Long id) {
		Optional<List<Boleto>> findById = boletoService.findByApartamentoId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj -> {
				ReflectionUtils.mapToBasicDTO(obj);
			});
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/faturamento/{id}")
	public ResponseEntity<List<Boleto>> buscarPorFaturamento(@PathVariable("id") Long id) {
		Optional<List<Boleto>> findById = boletoService.findByFaturamentoId(id);
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
	public ResponseEntity<List<Boleto>> buscarPorTitular(@PathVariable("id") Long id) {
		Optional<List<Boleto>> findById = boletoService.findByTitularId(id);
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
