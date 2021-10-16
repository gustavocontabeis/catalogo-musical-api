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

import br.com.codersistemas.condominiosadm.domain.Morador;
import br.com.codersistemas.condominiosadm.repository.ApartamentoRepository;
import br.com.codersistemas.condominiosadm.repository.MoradorRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;

@Service
public class MoradorService {

	@Autowired
	private MoradorRepository moradorRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ApartamentoRepository apartamentoRepository;

	
	@Transactional(readOnly = true)
	public List<Morador> findAll(Sort by) {
		return moradorRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Morador> findAll(Specification<Morador> specification, PageRequest pageRequest) {
		return moradorRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Morador> findById(Long id) {
		return moradorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Morador save(@Valid Morador entity) {
		return moradorRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(Morador morador) {
		moradorRepository.delete(morador);		
	}


	@Transactional(readOnly = true)
	public Optional<List<Morador>> findByPessoaId(Long id){
		return moradorRepository.findByPessoaId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Morador>> findByApartamentoId(Long id){
		return moradorRepository.findByApartamentoId(id);
	}

	

}

