package br.com.codersistemas.condominiosadm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
class CondominiosAdmApplicationTests {

	private Condominio condominio;
	
	private GeraCpfCnpj gemCpfCnpj;
	private GeradorPessoaJuridica gemPJ;
	private GeradorPessoaFisica gemPF;
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@Autowired
    private CondominioRepository condominioRepository;
	
	@BeforeEach
	public void init() {
		
		List<Pessoa> findAll = pessoaRepository.findAll();
		
		if(condominio != null) {
			return;
		}
		
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
		
		condominioRepository.save(condominio);
		
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
					Apartamento apartamento = new Apartamento();
					apartamento.setId(null);
					apartamento.setMoradores(new ArrayList<>());
					apartamento.setNumero("" + (100*(b+1) + c+1) );
					apartamento.setProprietario(proprietario);
					apartamento.getMoradores().add(proprietario);
					apartamento.setTitular(proprietario);
					bloco.getApartamentos().add(apartamento);
				}
			}
			
			Faturamento faturamento = new Faturamento();
			faturamento.setId(null);
			faturamento.setPeriodo(LocalDate.now().withDayOfMonth(1));
			faturamento.setCondominio(condominio);
			faturamento.setBoletos(new ArrayList<Boleto>());
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
					faturamento.getBoletos().add(boleto);
				}
			}
		}
		
	}

	/**
	 * FINANCEIRO:
	 * 	- Fluxo de caixa
	 * 		Cadastrar entradas e saídas, informando documentos, descrição, valores e saldo
	 * Relatório por Periodos, Anual, mensal, semanal, diario e personalizado
	 * Relatório por centro de custo
	 * Relatório por projeto - no caso de uma obra, lancar as despesas para uma detarminada obra, reforma ou projeto
	 * Registros vinculados a pessoa que gerou o registro.
	 * Poder ser o saldo atual do caixa
	 */
	@Test
	void fluxoDeCaixa() {
		
		//init();
		
		List<CentroDeCusto>listCentroDeCustos = new ArrayList<>();
		listCentroDeCustos.add(CentroDeCusto.builder().id(1L).nome("Recebimento de condomínio").build());
		listCentroDeCustos.add(CentroDeCusto.builder().id(2L).nome("Materiais de construção e reformas").build());
		listCentroDeCustos.add(CentroDeCusto.builder().id(3L).nome("Materiais de limpeza").build());
		listCentroDeCustos.add(CentroDeCusto.builder().id(4L).nome("Saque conta corrente").build());
		
		List<Caixa> listCaixa = new ArrayList<>();
		
		String[] x = new String[] {
				"1|0|Fulano de Tal|RECIBO|Recebimento condominio 409|400|1000",
				"2|1|Mazon materiais de construção|NOTA_FISCAL|Descricao 1|-230|770",
				"3|2|Caveiro XXX|NOTA_FISCAL|Descricao 1|-35|685",
				"4|2|Mazon materiais de construção|NOTA_FISCAL|Descricao 1|-72|613",
				"5|2|Mazon materiais de construção|NOTA_FISCAL|Descricao 1|-17|596",
				"6|2|Mazon materiais de construção|NOTA_FISCAL|Descricao 1|-100|496",
				"7|2|Mazon materiais de construção|NOTA_FISCAL|Descricao 1|-26|470",
				"8|4|Saque de conta corrente|NENHUM|Descricao 1|1500|1970",
				"9|2|Mazon materiais de construção|NOTA_FISCAL|Descricao 1|-50|1920",
					};
		for (String string : x) {
			String[] split = string.split("\\|");
			int i = 0;
			listCaixa.add(Caixa.builder()
					.id(new Long(split[i++]))
					.condominio(condominio)
					.pessoa(condominio.getSindico().getPessoa())
					.data(LocalDateTime.now())
					.centroDeCusto(listCentroDeCustos.get(new Integer(split[i++])))
					.para(split[i++])
					.cpfCnpj(gemCpfCnpj.cnpj(true))
					.tipoDocumento(TipoDocumento.valueOf(split[i++]))
					.descricao(split[i++])
					.valor(new BigDecimal(split[i++]))
					.saldo(new BigDecimal(split[i++]))
					.build());
		}
		
		for (Caixa cx : listCaixa) {
			log.info("{}", cx);
		}

	}

	@Test
	void imprtarExtratoBancario() {
		//init();
		String extrato = "31/12/21	4.843	DEPOSITO	20.000,00\n"
				+ "01/01/22	3.454	DEBITO EM CONTA	-79,25\n"
				+ "02/01/22	2.275	DEBITO EM CONTA	-24,88\n"
				+ "03/01/22	8.815	DEBITO EM CONTA	-3,28\n"
				+ "04/01/22	5.176	DEBITO EM CONTA	-22,74\n"
				+ "05/01/22	6.421	DEPOSITO	2.800,00\n"
				+ "06/01/22	2.060	DEBITO EM CONTA	-87,00\n"
				+ "07/01/22	8.432	DEBITO EM CONTA	-132,19\n"
				+ "08/01/22	6.801	DEBITO EM CONTA	-98,23\n"
				+ "09/01/22	1.887	DEBITO EM CONTA	-39,23\n"
				+ "10/01/22	3.165	DEBITO EM CONTA	-26,68\n"
				+ "11/01/22	6.516	DEBITO EM CONTA	-811,21\n"
				+ "12/01/22	7.923	DEBITO EM CONTA	-19,53\n"
				+ "13/01/22	9.784	DEBITO EM CONTA	-48,73\n"
				+ "14/01/22	379	DEBITO EM CONTA	-64,74\n"
				+ "15/01/22	1.188	DEBITO EM CONTA	-847,55\n"
				+ "16/01/22	8.348	DEBITO EM CONTA	-48,45\n"
				+ "17/01/22	7.553	DEBITO EM CONTA	-35,00\n"
				+ "18/01/22	2.905	DEBITO EM CONTA	-405,33\n"
				+ "19/01/22	9.335	DEBITO EM CONTA	-55,11\n"
				+ "20/01/22	5.337	DEBITO EM CONTA	-91,88\n"
				+ "21/01/22	6.982	DEBITO EM CONTA	-332,82\n"
				+ "22/01/22	2.245	DEBITO EM CONTA	-12,28\n"
				+ "23/01/22	6.591	DEBITO EM CONTA	-10,58\n"
				+ "24/01/22	441	DEBITO EM CONTA	-633,47\n"
				+ "25/01/22	3.065	DEBITO EM CONTA	-90,43\n"
				+ "26/01/22	8.820	DEBITO EM CONTA	-724,27\n"
				+ "27/01/22	346	DEBITO EM CONTA	-954,23\n"
				+ "28/01/22	6.899	DEBITO EM CONTA	-454,77\n"
				+ "29/01/22	4.634	DEBITO EM CONTA	-728,27\n"
				+ "30/01/22	3.522	DEBITO EM CONTA	-965,16\n"
				+ "31/01/22	2.898	DEBITO EM CONTA	-469,69\n"
				+ "01/02/22	2.240	DEBITO EM CONTA	-755,30\n"
				+ "02/02/22	4.852	DEBITO EM CONTA	-133,52\n"
				+ "03/02/22	4.616	DEBITO EM CONTA	-554,16\n"
				+ "04/02/22	1.385	DEBITO EM CONTA	-651,54\n"
				+ "05/02/22	4.531	DEBITO EM CONTA	-443,26\n"
				+ "06/02/22	1.159	DEBITO EM CONTA	-354,38\n"
				+ "07/02/22	2.712	DEBITO EM CONTA	-743,81\n"
				+ "08/02/22	3.904	DEBITO EM CONTA	-575,76\n"
				+ "09/02/22	1.902	DEBITO EM CONTA	-226,13\n"
				+ "10/02/22	1.968	DEBITO EM CONTA	-16,59\n";
		
		
		Banco b = new Banco();
		b.setId(01L);
		b.setNumero(1);
		b.setCondominio(condominio);
		b.setAgencia("1234");
		b.setConta("123456");
		b.setTipo(TipoContabancaria.CONTA_CORRENTE);
		b.setLancamentos(new ArrayList<>());
		
		String[] split = extrato.split("\n");
		BancoLancamento blAnterior = null;
		for (String linha : split) {
			String[] split2 = linha.split("\t");
			
			BancoLancamento bl = new BancoLancamento();
			bl.setBanco(b);
			bl.setCentroDeCusto(null);
			LocalDate parse = LocalDate.parse(split2[0], DateTimeFormatter.ofPattern("dd/MM/yy"));
			bl.setData(parse.atStartOfDay());
			bl.setDocumento(split2[1]);
			bl.setHistorico(split2[2]);
			bl.setId(null);
			bl.setValor(new BigDecimal(split2[3].replace(".", "").replace(",", ".")));
			bl.setSaldo(blAnterior == null ? bl.getValor() : blAnterior.getSaldo().add(bl.getValor()));
			blAnterior = bl;
			b.getLancamentos().add(bl);
		}
		
		for(BancoLancamento bl : b.getLancamentos()) {
			log.info("{}", bl);
		}

	}
	
	@Test
	void eleicaoDeSindico() {
		
	}
	
	@Test
	void marcarReunioes() {
		
	}

	@Test
	void gerarFaturamentoDesteMes() {
	}
	
	@Test
	void gerarArquivoParaBancoGerarBoletos() {
		
	}
	
	@Test
	void importarArquivosDeRecebimentoDoBanco() {
		
	}

	@Test
	void efetuarPagamentoManual() {
		
	}

	
	@Test
	void verificarInandimplentes() {
		
		//init();
		
		List<Faturamento> faturamentos = condominio.getFaturamentos();
		for (Faturamento faturamento : faturamentos) {
			for (Boleto boleto : faturamento.getBoletos()) {
				if(boleto.getPagamento() == null) {
					log.info("Boleto atrasado {}", boleto);
				}
			}
		}
	}

	@Test
	void regras() {
		
	}

	@Test
	void solicitarpermissao() {
		
	}

	@Test
	void servicos() {
		
	}

}
