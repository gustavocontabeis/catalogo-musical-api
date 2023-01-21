package br.com.codersistemas.condominiosadm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.codersistemas.condominiosadm.domain.Apartamento;
import br.com.codersistemas.condominiosadm.dto.FilterSpecification;
import br.com.codersistemas.condominiosadm.enums.QueryOperator;
import br.com.codersistemas.condominiosadm.repository.ApartamentoRepository;
import br.com.codersistemas.condominiosadm.repository.BlocoRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;

@Service
public class ApartamentoService {

	@Autowired
	private ApartamentoRepository apartamentoRepository;

	@Autowired
	private BlocoRepository blocoRepository;
	@Autowired
	private PessoaRepository proprietarioRepository;
	@Autowired
	private PessoaRepository titularRepository;

	
	@Transactional(readOnly = true)
	public List<Apartamento> findAll(Sort by) {
		return apartamentoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Apartamento> findAll(Specification<Apartamento> specification, PageRequest pageRequest) {
		return apartamentoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public List<Apartamento> findAllTeste() {
		FilterSpecification filter = new FilterSpecification();
		filter.setField("numero");
		filter.setOperator(QueryOperator.EQUALS);
		filter.setValue("101");
		List<Apartamento> findAll = apartamentoRepository.findAll(createSpecification(filter));
		return findAll;
	}

	@Transactional(readOnly = true)
	public Optional<Apartamento> findById(Long id) {
		return apartamentoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public Apartamento save(@Valid Apartamento entity) {
		return apartamentoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Apartamento apartamento) {
		apartamentoRepository.delete(apartamento);		
	}


	@Transactional(readOnly = true)
	public Optional<List<Apartamento>> findByBlocoId(Long id){
		return apartamentoRepository.findByBlocoId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Apartamento>> findByProprietarioId(Long id){
		return apartamentoRepository.findByProprietarioId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Apartamento>> findByTitularId(Long id){
		return apartamentoRepository.findByTitularId(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<List<Apartamento>> findByBlocoCondominioId(Long id) {
		return apartamentoRepository.findByBlocoCondominioId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Apartamento>> findBy(Long id) {
		return apartamentoRepository.findByBlocoCondominioId(id);
	}
	
	private Specification<Apartamento> createSpecification(FilterSpecification input) {
		switch (input.getOperator()){

		case EQUALS:
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get(input.getField()),
					castToRequiredType(root.get(input.getField()).getJavaType(), 
							input.getValue()));

		case NOT_EQUALS:
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.notEqual(root.get(input.getField()),
					castToRequiredType(root.get(input.getField()).getJavaType(), 
							input.getValue()));

		case GREATER_THAN:
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.gt(root.get(input.getField()),
					(Number) castToRequiredType(
							root.get(input.getField()).getJavaType(), 
							input.getValue()));

		case LESS_THAN:
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.lt(root.get(input.getField()),
					(Number) castToRequiredType(
							root.get(input.getField()).getJavaType(), 
							input.getValue()));

		case LIKE:
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.like(root.get(input.getField()), 
					"%"+input.getValue()+"%");

		case IN:
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.in(root.get(input.getField()))
			.value(castToRequiredType(
					root.get(input.getField()).getJavaType(), 
					input.getValues()));

		default:
			throw new RuntimeException("Operation not supported yet");
		}
	}

	private Object castToRequiredType(Class fieldType, String value) {
		if(fieldType.isAssignableFrom(Double.class)) {
			return Double.valueOf(value);
		} else if(fieldType.isAssignableFrom(String.class)) {
			return value;
		} else if(fieldType.isAssignableFrom(Integer.class)) {
			return Integer.valueOf(value);
		} else if(Enum.class.isAssignableFrom(fieldType)) {
			return Enum.valueOf(fieldType, value);
		}
		return null;
	}

	private Object castToRequiredType(Class fieldType, List<String> value) {
		List<Object> lists = new ArrayList<>();
		for (String s : value) {
			lists.add(castToRequiredType(fieldType, s));
		}
		return lists;
	}
}

