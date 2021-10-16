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

import br.com.codersistemas.condominiosadm.domain.Banco;
import br.com.codersistemas.condominiosadm.repository.BancoRepository;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;

@Service
public class BancoService {

	@Autowired
	private BancoRepository bancoRepository;

	@Autowired
	private CondominioRepository condominioRepository;

	
	@Transactional(readOnly = true)
	public List<Banco> findAll(Sort by) {
		return bancoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Banco> findAll(Specification<Banco> specification, PageRequest pageRequest) {
		return bancoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Banco> findById(Long id) {
		return bancoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Banco save(@Valid Banco entity) {
		return bancoRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(Banco banco) {
		bancoRepository.delete(banco);		
	}

	@Transactional(readOnly = true)	public Optional<List<Banco>> findByCondominioId(Long id){
		return bancoRepository.findByCondominioId(id);
	}

}

