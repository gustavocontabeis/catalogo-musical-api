package br.com.codersistemas.condominiosadm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.SindicoRepository;

@Service
public class CondominioService {

	@Autowired
	private CondominioRepository condominioRepository;

	@Autowired
	SindicoRepository sindicoRepository;

	
	public Optional<List<Condominio>> findBySindicoId(Long id){
		return condominioRepository.findBySindicoId(id);
	}

	public Condominio save(Condominio obj){
		return condominioRepository.save(obj);
	}

	public void delete(Condominio obj){
		condominioRepository.delete(obj);
	}

	public Optional<Condominio> findById(Long obj){
		return condominioRepository.findById(obj);
	}

	public Page<Condominio> findAll(Specification<Condominio> specification, Pageable pageable){
		return condominioRepository.findAll(specification, pageable);
	}

	public List<Condominio> findAll(){
		return condominioRepository.findAll();
	}
	
}