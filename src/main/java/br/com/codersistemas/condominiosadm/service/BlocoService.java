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

import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.repository.BlocoRepository;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;

@Service
public class BlocoService {

	@Autowired
	private BlocoRepository blocoRepository;

	@Autowired
	private CondominioRepository condominioRepository;
	
	@Transactional(readOnly = true)
	public List<Bloco> findAll(Sort by) {
		return blocoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Bloco> findAll(Specification<Bloco> specification, PageRequest pageRequest) {
		return blocoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Bloco> findById(Long id) {
		return blocoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Bloco save(@Valid Bloco entity) {
		return blocoRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(Bloco bloco) {
		blocoRepository.delete(bloco);		
	}

	@Transactional(readOnly = true)
	public Optional<List<Bloco>> findByCondominioId(Long id){
		return blocoRepository.findByCondominioId(id);
	}

}

