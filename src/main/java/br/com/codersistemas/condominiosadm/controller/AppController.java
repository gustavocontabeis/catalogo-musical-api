package br.com.codersistemas.condominiosadm.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codersistemas.condominiosadm.domain.Apartamento;
import br.com.codersistemas.condominiosadm.domain.Bloco;
import br.com.codersistemas.condominiosadm.domain.Boleto;
import br.com.codersistemas.condominiosadm.domain.Condominio;
import br.com.codersistemas.condominiosadm.domain.Faturamento;
import br.com.codersistemas.condominiosadm.domain.Morador;
import br.com.codersistemas.condominiosadm.domain.Pessoa;
import br.com.codersistemas.condominiosadm.domain.Sindico;
import br.com.codersistemas.condominiosadm.enums.Genero;
import br.com.codersistemas.condominiosadm.enums.TipoBloco;
import br.com.codersistemas.condominiosadm.repository.ApartamentoRepository;
import br.com.codersistemas.condominiosadm.repository.BlocoRepository;
import br.com.codersistemas.condominiosadm.repository.BoletoRepository;
import br.com.codersistemas.condominiosadm.repository.CondominioRepository;
import br.com.codersistemas.condominiosadm.repository.FaturamentoRepository;
import br.com.codersistemas.condominiosadm.repository.MoradorRepository;
import br.com.codersistemas.condominiosadm.repository.PessoaRepository;
import br.com.codersistemas.condominiosadm.service.MoradorService;
import br.com.codersistemas.gem.gemdados.GeraCpfCnpj;
import br.com.codersistemas.gem.gemdados.GeradorPessoaFisica;
import br.com.codersistemas.gem.gemdados.GeradorPessoaJuridica;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app")
public class AppController extends BaseController<Apartamento> {
	
	private Condominio condominio;
	
	private GeraCpfCnpj gemCpfCnpj;
	private GeradorPessoaJuridica gemPJ;
	private GeradorPessoaFisica gemPF;
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@Autowired
    private CondominioRepository condominioRepository;
	
	@Autowired
    private BlocoRepository blocoRepository;
	
	@Autowired
    private ApartamentoRepository apartamentoRepository;
	
	@Autowired
    private FaturamentoRepository faturamentoRepository;
	
	@Autowired
    private BoletoRepository boletoRepository;
	
	@Autowired
    private MoradorService moradorRepository;
	
	//@EventListener(ApplicationReadyEvent.class)
	public void init() {
		
		gemCpfCnpj = new GeraCpfCnpj();
		gemPJ = new GeradorPessoaJuridica();
		
		condominio = new Condominio();
		condominio.setCidade("Porto Alegre");
		condominio.setBairro("Sarandi");
		condominio.setLogradouro("Rua Engenheiro Sadi Castro");
		condominio.setNome("Ed. Monalisa");
		condominio.setNumero("481");
		condominio.setFaturamentos(new ArrayList<>());
		//condominio.setGaragens(new ArrayList<>());
		
		
		Sindico sindico = new Sindico();
		//sindico.setId(null);
		sindico.setPessoa(new Pessoa());
		sindico.getPessoa().setNome("Joao da Silva");
		sindico.getPessoa().setGenero(Genero.MASCULINO);
		sindico.getPessoa().setNascimento(LocalDate.now().withDayOfMonth(27).withMonth(8).withYear(1978));
		sindico.getPessoa().setCpf("745.936.370-74");
		sindico.setDe(LocalDate.now().withDayOfMonth(1).withMonth(1));
		sindico.setAte(LocalDate.now().withDayOfMonth(31).withMonth(12));
		condominio.setSindico(sindico);
		
		condominioRepository.saveAndFlush(condominio);
		log.info("Condomínio: {}", condominio.getId());
		log.info("Síndico: {}", condominio.getSindico().getPessoa().getId());
		
		List<Bloco> blocos = new ArrayList<Bloco>();
		String[] nomeBlocos = new String[]{"Bloco Sul", "Bloco Norte"};
		for (int a = 0; a < nomeBlocos.length; a++) {
			Bloco bloco = new Bloco();
			List<Apartamento> apartamentos = new ArrayList<>();
			bloco.setApartamentos(apartamentos);
			bloco.setCondominio(condominio);
			bloco.setId(null);
			bloco.setNome(nomeBlocos[a]);
			bloco.setTipo(TipoBloco.BLOCO);
			blocoRepository.saveAndFlush(bloco);
			log.info("---> {}", bloco);
			blocos.add(bloco);
			condominio.setBlocos(blocos);
			for (int b = 0; b < 10; b++) {
				for (int c = 0; c < 4; c++) {
					gemPF = new GeradorPessoaFisica();
					Pessoa proprietario = Pessoa.builder()
							.cpf(gemCpfCnpj.cpf(true))
							.nome(gemPF.gerarNome(null))
							.genero(Genero.MASCULINO)
							.nascimento(LocalDate.now().withDayOfMonth(23).withMonth(3).withYear(1983))
							.build();
					pessoaRepository.saveAndFlush(proprietario);
					Apartamento apartamento = new Apartamento();
					apartamento.setId(null);
					apartamento.setBloco(bloco);
					apartamento.setMoradores(new ArrayList<>());
					apartamento.setNumero("" + (100*(b+1) + c+1) );
					apartamento.setProprietario(proprietario);
					apartamento.getMoradores().add(proprietario);
					apartamento.setTitular(proprietario);
					apartamentoRepository.saveAndFlush(apartamento);
					log.info("---> {}", apartamento);
					log.info("---> {}", apartamento.getTitular());
					int quantMoradores = new Random().nextInt(5);
					quantMoradores = (quantMoradores == 0 ? 1 : quantMoradores);
					boolean prop = true;
					for (int f = 0; f < quantMoradores; f++) {
						Pessoa proprietarioMorador = Pessoa.builder()
								.cpf(gemCpfCnpj.cpf(true))
								.nome(gemPF.gerarNome(null))
								.genero(Genero.MASCULINO)
								.nascimento(LocalDate.now().withDayOfMonth(23).withMonth(3).withYear(1983))
								.build();
						Morador morador = new Morador(proprietarioMorador);
						morador.setProprietario(prop);
						if(prop) {
							prop = false;
						}
						morador.setApartamento(apartamento);
						moradorRepository.save(morador);
					}
					bloco.getApartamentos().add(apartamento);
				}
			}
			
			Faturamento faturamento = new Faturamento();
			faturamento.setId(null);
			faturamento.setPeriodo(LocalDate.now().withDayOfMonth(1));
			faturamento.setCondominio(condominio);
			faturamento.setBoletos(new ArrayList<Boleto>());
			faturamentoRepository.saveAndFlush(faturamento);
			log.info("---> Fatura: {}", faturamento);
			condominio.getFaturamentos().add(faturamento);
			for (Bloco blocoItem : condominio.getBlocos()) {
				for (Apartamento apartamento : blocoItem.getApartamentos()) {
					Boleto boleto = new Boleto();
					boleto.setApartamento(apartamento);
					boleto.setId(null);
					boleto.setTitular(apartamento.getTitular() != null ? apartamento.getTitular() : apartamento.getProprietario());
					boleto.setJuros(BigDecimal.ZERO);
					boleto.setMulta(BigDecimal.ZERO);
					boleto.setPagamento(null);
					boleto.setTotal(BigDecimal.ZERO);
					boleto.setValor(new BigDecimal(400));
					boleto.setVencimento(LocalDate.now().withDayOfMonth(10));
					boleto.setFaturamento(faturamento);
					boletoRepository.saveAndFlush(boleto);
					log.info("---> Boleto: {}", boleto);
					faturamento.getBoletos().add(boleto);
				}
			}
		}
		log.info("-------");
		if(true) {
			return;
		}
		
		List<Condominio> findAll = condominioRepository.findAll();
		for (Condominio condominio : findAll) {
			log.info("{}", condominio);
			Optional<List<Bloco>> findByCondominioId = blocoRepository.findByCondominioId(condominio.getId());
			if(findByCondominioId.isPresent()) {
				for (Bloco bloco : findByCondominioId.get()) {
					log.info("{}", bloco);
					Optional<List<Apartamento>> findByBlocoId = apartamentoRepository.findByBlocoId(bloco.getId());
					if(findByBlocoId.isPresent()) {
						for (Apartamento apartamento : findByBlocoId.get()) {
							log.info("{}", apartamento);
							Optional<List<Morador>> findByApartamentoId = moradorRepository.findByApartamentoId(apartamento.getId());
							if(findByApartamentoId.isPresent()) {
								for (Morador m : findByApartamentoId.get()) {
									log.info("{}", m);	
								}
							}
						}
					}
				}
			}
			Optional<List<Faturamento>> findByCondominioId2 = faturamentoRepository.findByCondominioId(condominio.getId());
			if(findByCondominioId2.isPresent()) {
				for (Faturamento f : findByCondominioId2.get()) {
					log.info("{}", f);
				}
			}
		}
	}

	
	
}

