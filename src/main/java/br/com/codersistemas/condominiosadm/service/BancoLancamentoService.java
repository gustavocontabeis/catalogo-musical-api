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

import br.com.codersistemas.condominiosadm.domain.BancoLancamento;
import br.com.codersistemas.condominiosadm.repository.BancoLancamentoRepository;
import br.com.codersistemas.condominiosadm.repository.BancoRepository;
import br.com.codersistemas.condominiosadm.repository.CentroDeCustoRepository;

@Service
public class BancoLancamentoService {

	@Autowired
	private BancoLancamentoRepository bancoLancamentoRepository;

	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;

	@Transactional(readOnly = true)
	public List<BancoLancamento> findAll(Sort by) {
		return bancoLancamentoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<BancoLancamento> findAll(Specification<BancoLancamento> specification, PageRequest pageRequest) {
		return bancoLancamentoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<BancoLancamento> findById(Long id) {
		return bancoLancamentoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public BancoLancamento save(@Valid BancoLancamento entity) {
		return bancoLancamentoRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public void delete(BancoLancamento bancoLancamento) {
		bancoLancamentoRepository.delete(bancoLancamento);
	}

	@Transactional(readOnly = true)
	public Optional<List<BancoLancamento>> findByBancoId(Long id) {
		return bancoLancamentoRepository.findByBancoId(id);
	}

	@Transactional(readOnly = true)
	public Optional<List<BancoLancamento>> findByCentroDeCustoId(Long id) {
		return bancoLancamentoRepository.findByCentroDeCustoId(id);
	}

}
