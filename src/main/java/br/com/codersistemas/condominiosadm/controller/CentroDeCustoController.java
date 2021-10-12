package br.com.codersistemas.condominiosadm.controller;

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

import br.com.codersistemas.condominiosadm.domain.CentroDeCusto;
import br.com.codersistemas.condominiosadm.repository.CentroDeCustoRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/centroDeCustos")
public class CentroDeCustoController {
	
	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;
	
	@GetMapping
	public List<CentroDeCusto> listar() {
		log.debug("listar!");
		List<CentroDeCusto> findAll = centroDeCustoRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<CentroDeCusto> buscar(@PathVariable Long id) {
		Optional<CentroDeCusto> findById = centroDeCustoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CentroDeCusto adicionar(@Valid @RequestBody CentroDeCusto entity) {
		return centroDeCustoRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CentroDeCusto> excluir(@PathVariable Long id) {
		Optional<CentroDeCusto> findById = centroDeCustoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		centroDeCustoRepository.delete(findById.get());
		return new ResponseEntity<CentroDeCusto>(HttpStatus.NO_CONTENT);
	}

}

