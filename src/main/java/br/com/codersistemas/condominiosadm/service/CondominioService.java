package br.com.codersistemas.condominiosadm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Sindico;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.SindicoRepository;

@Service
public class CondominioService {

	@Autowired
	private CondominioRepository condominioRepository;
	
	@Autowired
	private SindicoRepository sindicoRepository;

	@Transactional(readOnly = true)
	public Optional<List<Condominio>> findBySindicoId(Long id){
		return condominioRepository.findBySindicoId(id);
	}
	
	@Transactional(readOnly = false)
	public Condominio save(Condominio obj){
		obj.setSindico(sindicoRepository.findById(obj.getSindico().getId()).get());
		return condominioRepository.save(obj);
	}

	@Transactional(readOnly = false)
	public void delete(Condominio obj){
		condominioRepository.delete(obj);
	}

	@Transactional(readOnly = true)
	public Optional<Condominio> findById(Long obj){
		return condominioRepository.findById(obj);
	}

	@Transactional(readOnly = true)
	public Page<Condominio> findAll(Specification<Condominio> specification, Pageable pageable){
		return condominioRepository.findAll(specification, pageable);
	}

	@Transactional(readOnly = true)
	public List<Condominio> findAll(){
		return condominioRepository.findAll();
	}
	
}