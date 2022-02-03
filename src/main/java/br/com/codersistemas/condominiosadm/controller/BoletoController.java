package br.com.codersistemas.condominiosadm.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import br.com.codersistemas.condominiosadm.dto.Totais;
import br.com.codersistemas.condominiosadm.service.BoletoService;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/boletos")
public class BoletoController extends BaseController<Boleto> {
	
	@Autowired
	private BoletoService boletoService;
	
	@GetMapping
	public List<Boleto> listar() {
		return boletoService.findAll(Sort.by(Order.desc("id")));
	}
	
	@PostMapping("/page")
	public Page<Boleto> listar(@RequestBody LazyLoadEvent event) {
		Specification<Boleto> specification = createSpecification(event);
		PageRequest pageRequest = getPageRequest(event);
		return boletoService.findAll(specification, pageRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Boleto> buscar(@PathVariable Long id) {
		Optional<Boleto> findById = boletoService.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@GetMapping("/to-pay/{id}")
	public ResponseEntity<Boleto> pagar(@PathVariable Long id) {
		Optional<Boleto> findById = boletoService.toPay(id);
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
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/faturamento/{id}")
	public ResponseEntity<List<Boleto>> buscarPorFaturamento(@PathVariable("id") Long id) {
		Optional<List<Boleto>> findById = boletoService.findByFaturamentoId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}
		return ResponseEntity.ok(findById.get());
	}

	@GetMapping("/totais/faturamento/{id}")
	public ResponseEntity<List<Totais>> buscarTotaisPorFaturamento(@PathVariable("id") Long id) {
		Optional<List<Boleto>> findById = boletoService.findByFaturamentoId(id);
		if(findById.isPresent()) {
			
			List<Boleto> list = findById.get();
			List<Totais> totais = new ArrayList<>();
			
			totais.add(Totais.builder()
					.descricao("Todos")
					.valor(list.stream().map(Boleto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add))
					.multa(list.stream().map(Boleto::getMulta).reduce(BigDecimal.ZERO, BigDecimal::add))
					.juros(list.stream().map(Boleto::getJuros).reduce(BigDecimal.ZERO, BigDecimal::add))
					.total(list.stream().map(Boleto::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add))
					.build());
			
			totais.add(Totais.builder()
					.descricao("Nao pagos")
					.valor(list.stream().filter(b->b.getPagamento()==null).map(Boleto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add))
					.multa(list.stream().filter(b->b.getPagamento()==null).map(Boleto::getMulta).reduce(BigDecimal.ZERO, BigDecimal::add))
					.juros(list.stream().filter(b->b.getPagamento()==null).map(Boleto::getJuros).reduce(BigDecimal.ZERO, BigDecimal::add))
					.total(list.stream().filter(b->b.getPagamento()==null).map(Boleto::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add))
					.build());

			totais.add(Totais.builder()
					.descricao("Pagos")
					.valor(list.stream().filter(b->b.getPagamento()!=null).map(Boleto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add))
					.multa(list.stream().filter(b->b.getPagamento()!=null).map(Boleto::getMulta).reduce(BigDecimal.ZERO, BigDecimal::add))
					.juros(list.stream().filter(b->b.getPagamento()!=null).map(Boleto::getJuros).reduce(BigDecimal.ZERO, BigDecimal::add))
					.total(list.stream().filter(b->b.getPagamento()!=null).map(Boleto::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add))
					.build());
			
			return ResponseEntity.ok(totais);
		}
		return ResponseEntity.ok(Collections.EMPTY_LIST);
	}

	@GetMapping("/titular/{id}")
	public ResponseEntity<List<Boleto>> buscarPorTitular(@PathVariable("id") Long id) {
		Optional<List<Boleto>> findById = boletoService.findByTitularId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}
		return ResponseEntity.ok(findById.get());
	}

}
