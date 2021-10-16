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

import br.com.codersistemas.condominiosadm.domain.CentroDeCusto;
import br.com.codersistemas.condominiosadm.repository.CentroDeCustoRepository;

@Service
public class CentroDeCustoService {

	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;


	
	@Transactional(readOnly = true)
	public List<CentroDeCusto> findAll(Sort by) {
		return centroDeCustoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<CentroDeCusto> findAll(Specification<CentroDeCusto> specification, PageRequest pageRequest) {
		return centroDeCustoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<CentroDeCusto> findById(Long id) {
		return centroDeCustoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public CentroDeCusto save(@Valid CentroDeCusto entity) {
		return centroDeCustoRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(CentroDeCusto centroDeCusto) {
		centroDeCustoRepository.delete(centroDeCusto);		
	}

}

