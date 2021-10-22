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

import br.com.codersistemas.condominiosadm.domain.BancoLancamento;
import br.com.codersistemas.condominiosadm.dto.LazyLoadEvent;
import br.com.codersistemas.condominiosadm.service.BancoLancamentoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/banco-lancamentos")
public class BancoLancamentoController extends BaseController<BancoLancamento> {
	
	@Autowired
	private BancoLancamentoService bancoLancamentoService;
	
	@GetMapping
	public List<BancoLancamento> listar() {
		return bancoLancamentoService.findAll(Sort.by(Order.asc("banco.id"), Order.asc("id")));
	}
	
	@PostMapping("/page")
	public Page<BancoLancamento> listar(@RequestBody LazyLoadEvent event) {
		log.info("{}", event);
		Specification<BancoLancamento> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		return bancoLancamentoService.findAll(specification, pageRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BancoLancamento> buscar(@PathVariable Long id) {
		Optional<BancoLancamento> findById = bancoLancamentoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public BancoLancamento adicionar(@Valid @RequestBody BancoLancamento entity) {
		return bancoLancamentoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BancoLancamento> excluir(@PathVariable Long id) {
		Optional<BancoLancamento> findById = bancoLancamentoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bancoLancamentoService.delete(findById.get());
		return new ResponseEntity<BancoLancamento>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/banco/{id}")
	public ResponseEntity<List<BancoLancamento>> buscarPorBanco(@PathVariable("id") Long id) {
		Optional<List<BancoLancamento>> findById = bancoLancamentoService.findByBancoId(id);
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
	public ResponseEntity<List<BancoLancamento>> buscarPorCentroDeCusto(@PathVariable("id") Long id) {
		Optional<List<BancoLancamento>> findById = bancoLancamentoService.findByCentroDeCustoId(id);
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

