package br.com.codersistemas.condominiosadm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codersistemas.condominiosadm.domain.Caixa;
import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.repository.CaixaRepository;

@Service
public class CaixaService {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	public Caixa save(Caixa caixa) {
		Condominio condominio = caixa.getCondominio();
		Caixa last = findLast(condominio);
		caixa.setSaldo(last.getSaldo().add(caixa.getValor()));
		save(caixa);
		return caixa;
	}

	private Caixa findLast(Condominio condominio) {
		return caixaRepository.findLast(condominio);
	}

	private Caixa saves(Caixa caixa) {
		return caixaRepository.save(caixa);
	}

}
