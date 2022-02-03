package br.com.codersistemas.condominiosadm.service;
	

import java.math.BigDecimal;
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
import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.domain.Boleto;
import br.com.codersistemas.condominiosadm.domain.Faturamento;
import br.com.codersistemas.condominiosadm.repository.BlocoRepository;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.FaturamentoRepository;

@Service
public class FaturamentoService {

	@Autowired
	private FaturamentoRepository faturamentoRepository;

	@Autowired
	private CondominioRepository condominioRepository;
	
	@Autowired
	private BlocoService blocoService;

	@Autowired
	private ApartamentoService apartamentoService;

	@Autowired
	private BoletoService boletoService;

	
	@Transactional(readOnly = true)
	public List<Faturamento> findAll(Sort by) {
		return faturamentoRepository.findAll(by);
	}

	@Transactional(readOnly = true)
	public Page<Faturamento> findAll(Specification<Faturamento> specification, PageRequest pageRequest) {
		return faturamentoRepository.findAll(specification, pageRequest);
	}

	@Transactional(readOnly = true)
	public Optional<Faturamento> findById(Long id) {
		return faturamentoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public Faturamento save(@Valid Faturamento entity) {
		@Valid
		Faturamento save = faturamentoRepository.save(entity);
		Optional<List<Bloco>> blocos = blocoService.findByCondominioId(save.getCondominio().getId());
		if(blocos.isPresent()) {
			List<Bloco> list = blocos.get();
			for (Bloco bloco : list) {
				Optional<List<Apartamento>> apartamentoOptional = apartamentoService.findByBlocoId(bloco.getId());
				if(apartamentoOptional.isPresent()) {
					List<Apartamento> apartamentos = apartamentoOptional.get();
					apartamentos.forEach(ap->saveBloleto(ap, entity));
				}
			}
		}
		return save;
	}

	private void saveBloleto(Apartamento ap, Faturamento entity) {
		boletoService.save(Boleto.builder()
				.apartamento(ap)
				.faturamento(entity)
				.juros(BigDecimal.ZERO)
				.multa(BigDecimal.ZERO)
				.valor(new BigDecimal(400))
				.total(BigDecimal.ZERO)
				.vencimento(entity.getPeriodo().withDayOfMonth(10))
				.titular(ap.getTitular())
				.build());
	}

	@Transactional(readOnly = false)
	public void delete(Faturamento faturamento) {
		faturamentoRepository.delete(faturamento);		
	}
	
	@Transactional(readOnly = true)
	public Optional<List<Faturamento>> findByCondominioId(Long id){
		return faturamentoRepository.findByCondominioId(id);
	}

}

