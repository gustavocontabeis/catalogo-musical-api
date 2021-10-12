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

import br.com.codersistemas.condominiosadm.domain.BancoLancamento;
import br.com.codersistemas.condominiosadm.repository.BancoLancamentoRepository;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/banco-lancamentos")
public class BancoLancamentoController {
	
	@Autowired
	private BancoLancamentoRepository bancoLancamentoRepository;
	
	@GetMapping
	public List<BancoLancamento> listar() {
		log.debug("listar!");
		List<BancoLancamento> findAll = bancoLancamentoRepository.findAll(Sort.by(Order.asc("nome"))); 
		findAll.forEach(obj -> {
			ReflectionUtils.mapToBasicDTO(obj);
		});
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<BancoLancamento> buscar(@PathVariable Long id) {
		Optional<BancoLancamento> findById = bancoLancamentoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public BancoLancamento adicionar(@Valid @RequestBody BancoLancamento entity) {
		return bancoLancamentoRepository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BancoLancamento> excluir(@PathVariable Long id) {
		Optional<BancoLancamento> findById = bancoLancamentoRepository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bancoLancamentoRepository.delete(findById.get());
		return new ResponseEntity<BancoLancamento>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/banco/{id}")
	public ResponseEntity<List<BancoLancamento>> buscarPorBanco(@PathVariable("id") Long id) {
		Optional<List<BancoLancamento>> findById = bancoLancamentoRepository.findByBancoId(id);
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
		Optional<List<BancoLancamento>> findById = bancoLancamentoRepository.findByCentroDeCustoId(id);
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
