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

import br.com.codersistemas.condominiosadm.domain.Caixa;
import br.com.codersistemas.condominiosadm.repository.CaixaRepository;
import br.com.codersistemas.condominiosadm.repository.CentroDeCustoRepository;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;

@Service
public class CaixaService {

	@Autowired
	private CaixaRepository caixaRepository;

	@Autowired
	private CondominioRepository condominioRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;
	
	@Transactional(readOnly = true)
	public List<Caixa> findAll(Sort by) {
		return caixaRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Caixa> findAll(Specification<Caixa> specification, PageRequest pageRequest) {
		return caixaRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Caixa> findById(Long id) {
		return caixaRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public Caixa save(@Valid Caixa entity) {
		entity.setCentroDeCusto(centroDeCustoRepository.findById(entity.getCentroDeCusto().getId()).get());
		return caixaRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Caixa caixa) {
		caixaRepository.delete(caixa);		
	}

	@Transactional(readOnly = true)
	public Optional<List<Caixa>> findByCondominioId(Long id){
		return caixaRepository.findByCondominioId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Caixa>> findByPessoaId(Long id){
		return caixaRepository.findByPessoaId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Caixa>> findByCentroDeCustoId(Long id){
		return caixaRepository.findByCentroDeCustoId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<Caixa>> findLastByCondominio(Long idCondominio) {
		return caixaRepository.findLastByCondominio(idCondominio);
	}

}

