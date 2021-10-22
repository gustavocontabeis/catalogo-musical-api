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

import br.com.codersistemas.condominiosadm.domain.Pessoa;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;


	
	@Transactional(readOnly = true)
	public List<Pessoa> findAll(Sort by) {
		return pessoaRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Pessoa> findAll(Specification<Pessoa> specification, PageRequest pageRequest) {
		return pessoaRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public Pessoa save(@Valid Pessoa entity) {
		return pessoaRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Pessoa pessoa) {
		pessoaRepository.delete(pessoa);		
	}

}

