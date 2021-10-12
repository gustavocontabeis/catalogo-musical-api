package br.com.codersistemas.condominiosadm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.codersistemas.condominiosadm.domain.Apartamento;
import br.com.codersistemas.condominiosadm.domain.Banco;
import br.com.codersistemas.condominiosadm.domain.BancoLancamento;
import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.domain.Boleto;
import br.com.codersistemas.condominiosadm.domain.Caixa;
import br.com.codersistemas.condominiosadm.domain.CentroDeCusto;
import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Faturamento;
import br.com.codersistemas.condominiosadm.domain.Pessoa;
import br.com.codersistemas.condominiosadm.domain.Sindico;
import br.com.codersistemas.condominiosadm.enums.Genero;
import br.com.codersistemas.condominiosadm.enums.TipoBloco;
import br.com.codersistemas.condominiosadm.enums.TipoContabancaria;
import br.com.codersistemas.condominiosadm.enums.TipoDocumento;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;
import br.com.codersistemas.gem.gemdados.GeraCpfCnpj;
import br.com.codersistemas.gem.gemdados.GeradorPessoaFisica;
import br.com.codersistemas.gem.gemdados.GeradorPessoaJuridica;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CondominioTest {

	private Condominio condominio;
	
	@Autowired
    private CondominioRepository condominioRepository;
	
	@BeforeEach
	public void init() {
		
		condominio = new Condominio();
		condominio.setCidade("Porto Alegre");
		condominio.setBairro("Sarandi");
		condominio.setLogradouro("Rua Engenheiro Sadi Castro");
		condominio.setNome("Ed. Monalisa");
		condominio.setNumero("481");
		condominio.setFaturamentos(new ArrayList<>());
		
		Sindico sindico = new Sindico();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(null);
		pessoa.setNome("Joao da Silva");
		pessoa.setGenero(Genero.MASCULINO);
		pessoa.setNascimento(LocalDate.now().withDayOfMonth(27).withMonth(8).withYear(1978));
		pessoa.setCpf("745.936.370-74");
		sindico.setPessoa(pessoa);
		sindico.setDe(LocalDate.now().withDayOfMonth(1).withMonth(1));
		sindico.setAte(LocalDate.now().withDayOfMonth(31).withMonth(12));
		condominio.setSindico(sindico);
		
	}
	
	@Test
	public void crud() {
		
		condominioRepository.deleteAll();
		
		condominioRepository.save(condominio);
		assertNotNull(condominio.getId());
		
		String updateValue = "Monalisa2";
		condominio.setNome(updateValue);
		condominioRepository.save(condominio);
		
		Condominio one = condominioRepository.getOne(condominio.getId());
		assertEquals(updateValue, one.getNome());
		
		condominioRepository.delete(condominio);

	}

	@Test
	public void findBySindicoId() {
		
		condominioRepository.save(condominio);
		
		Optional<List<Condominio>> findBySindicoId = condominioRepository.findBySindicoId(condominio.getSindico().getId());
		log.info("{}", findBySindicoId.isPresent());
		if(findBySindicoId.isPresent()) {
			log.info("{}", findBySindicoId.get());	
		}
	}

}
