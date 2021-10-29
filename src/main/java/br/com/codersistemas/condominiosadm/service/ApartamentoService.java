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

import br.com.codersistemas.condominiosadm.domain.Apartamento;
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

}

