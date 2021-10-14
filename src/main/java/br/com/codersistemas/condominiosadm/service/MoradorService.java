package br.com.codersistemas.condominiosadm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.codersistemas.condominiosadm.domain.Morador;
import br.com.codersistemas.condominiosadm.domain.Pessoa;
import br.com.codersistemas.condominiosadm.repository.MoradorRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;
import br.com.codersistemas.libs.utils.StringUtil;

@Service
public class MoradorService {

	@Autowired
	private MoradorRepository moradorRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional()
	public void save(Morador morador) {
		Pessoa pessoa = morador.getPessoa();
		if(StringUtil.isNotBlank(pessoa.getCpf())) {
			Optional<Pessoa> findByCpf = pessoaRepository.findByCpf(pessoa.getCpf());
			if(findByCpf.isPresent()) {
				morador.setPessoa(findByCpf.get());
			}
		}
		moradorRepository.save(morador);
	}

	public Optional<List<Morador>> findByApartamentoId(Long id) {
		return moradorRepository.findByApartamentoId(id);
	}
	
}