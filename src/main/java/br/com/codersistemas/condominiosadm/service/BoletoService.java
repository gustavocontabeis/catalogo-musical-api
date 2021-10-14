package br.com.codersistemas.condominiosadm.service;


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

import br.com.codersistemas.condominiosadm.domain.Boleto;
import br.com.codersistemas.condominiosadm.repository.ApartamentoRepository;
import br.com.codersistemas.condominiosadm.repository.BoletoRepository;
import br.com.codersistemas.condominiosadm.repository.FaturamentoRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;

@Service
public class BoletoService {

	@Autowired
	private BoletoRepository boletoRepository;

	@Autowired
	private ApartamentoRepository apartamentoRepository;
	
	@Autowired
	private FaturamentoRepository faturamentoRepository;
	
	@Autowired
	private PessoaRepository titularRepository;
	
	@Transactional(readOnly = true)
	public List<Boleto> findAll(Sort by) {
		return boletoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Boleto> findAll(Specification<Boleto> specification, PageRequest pageRequest) {
		return boletoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Boleto> findById(Long id) {
		return boletoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Boleto save(@Valid Boleto entity) {
		return boletoRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(Boleto boleto) {
		boletoRepository.delete(boleto);		
	}

	@Transactional(readOnly = true)	public Optional<List<Boleto>> findByApartamentoId(Long id){
		return boletoRepository.findByApartamentoId(id);
	}

	@Transactional(readOnly = true)	public Optional<List<Boleto>> findByFaturamentoId(Long id){
		return boletoRepository.findByFaturamentoId(id);
	}

	@Transactional(readOnly = true)	public Optional<List<Boleto>> findByTitularId(Long id){
		return boletoRepository.findByTitularId(id);
	}

}

