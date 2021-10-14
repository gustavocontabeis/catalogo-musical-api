package br.com.codersistemas.condominiosadm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codersistemas.condominiosadm.domain.Pessoa;
import br.com.codersistemas.condominiosadm.domain.Sindico;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;
import br.com.codersistemas.condominiosadm.repository.SindicoRepository;

@Service
public class SindicoService {

	@Autowired
	private SindicoRepository sindicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	
	public void save(Sindico sindico) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(sindico.getPessoa().getCpf());
		if(pessoa.isPresent()) {
			sindico.setPessoa(pessoa.get());
		}
		
		sindicoRepository.save(sindico);
	}
	
	
//	public Optional<List<Sindico>> findByPessoaId(Long id){
//		return sindicoRepository.findByPessoaId();
//	}

	
}