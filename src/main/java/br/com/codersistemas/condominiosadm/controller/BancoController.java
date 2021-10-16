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

import br.com.codersistemas.condominiosadm.domain.Banco;
import br.com.codersistemas.condominiosadm.service.BancoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bancos")
public class BancoController {
	
	@Autowired
	private BancoService bancoService;
	
	@GetMapping
	public List<Banco> listar() {
		log.debug("listar!");
		List<Banco> findAll = bancoService.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Banco> buscar(@PathVariable Long id) {
		Optional<Banco> findById = bancoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Banco adicionar(@Valid @RequestBody Banco entity) {
		return bancoService.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Banco> excluir(@PathVariable Long id) {
		Optional<Banco> findById = bancoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bancoService.delete(findById.get());
		return new ResponseEntity<Banco>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/condominio/{id}")
	public ResponseEntity<List<Banco>> buscarPorCondominio(@PathVariable("id") Long id) {
		Optional<List<Banco>> findById = bancoService.findByCondominioId(id);
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

