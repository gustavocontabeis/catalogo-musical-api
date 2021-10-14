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

import br.com.codersistemas.condominiosadm.domain.Faturamento;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.FaturamentoRepository;

@Service
public class FaturamentoService {

	@Autowired
	private FaturamentoRepository faturamentoRepository;

	@Autowired
	private CondominioRepository condominioRepository;

	
	@Transactional(readOnly = true)
	public List<Faturamento> findAll(Sort by) {
		return faturamentoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Faturamento> findAll(Specification<Faturamento> specification, PageRequest pageRequest) {
		return faturamentoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Faturamento> findById(Long id) {
		return faturamentoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Faturamento save(@Valid Faturamento entity) {
		return faturamentoRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(Faturamento faturamento) {
		faturamentoRepository.delete(faturamento);		
	}

	public Optional<List<Faturamento>> findByCondominioId(Long id){
		return faturamentoRepository.findByCondominioId(id);
	}

}

