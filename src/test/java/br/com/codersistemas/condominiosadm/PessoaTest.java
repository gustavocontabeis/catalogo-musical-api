/*
 * package br.com.codersistemas.condominiosadm;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.junit.Assert.assertNotNull; import static org.junit.Assert.assertTrue;
 * 
 * import java.math.BigDecimal; import java.time.LocalDate; import
 * java.time.LocalDateTime; import java.time.format.DateTimeFormatter; import
 * java.util.ArrayList; import java.util.List; import java.util.Optional;
 * 
 * import org.junit.Before; import org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * import br.com.codersistemas.condominiosadm.domain.Apartamento; import
 * br.com.codersistemas.condominiosadm.domain.Banco; import
 * br.com.codersistemas.condominiosadm.domain.BancoLancamento; import
 * br.com.codersistemas.condominiosadm.domain.Bloco; import
 * br.com.codersistemas.condominiosadm.domain.Boleto; import
 * br.com.codersistemas.condominiosadm.domain.Caixa; import
 * br.com.codersistemas.condominiosadm.domain.CentroDeCusto; import
 * br.com.codersistemas.condominiosadm.domain.Condominio; import
 * br.com.codersistemas.condominiosadm.domain.Faturamento; import
 * br.com.codersistemas.condominiosadm.domain.Morador; import
 * br.com.codersistemas.condominiosadm.domain.Pessoa; import
 * br.com.codersistemas.condominiosadm.domain.Sindico; import
 * br.com.codersistemas.condominiosadm.enums.Genero; import
 * br.com.codersistemas.condominiosadm.enums.TipoBloco; import
 * br.com.codersistemas.condominiosadm.enums.TipoContabancaria; import
 * br.com.codersistemas.condominiosadm.enums.TipoDocumento; import
 * br.com.codersistemas.condominiosadm.repository.CondominioRepository; import
 * br.com.codersistemas.condominiosadm.repository.MoradorRepository; import
 * br.com.codersistemas.condominiosadm.repository.PessoaRepository; import
 * br.com.codersistemas.condominiosadm.repository.SindicoRepository; import
 * br.com.codersistemas.gem.gemdados.GeraCpfCnpj; import
 * br.com.codersistemas.gem.gemdados.GeradorPessoaFisica; import
 * br.com.codersistemas.gem.gemdados.GeradorPessoaJuridica; import
 * lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j
 * 
 * @SpringBootTest class PessoaTest {
 * 
 * @Autowired private PessoaRepository pessoaRepository;
 * 
 * @Autowired private SindicoRepository sindicoRepository;
 * 
 * @Autowired private MoradorRepository moradorRepository;
 * 
 * @BeforeEach public void init() {
 * 
 * Sindico sindico = new Sindico(); sindico.setPessoa(new Pessoa());
 * sindico.getPessoa().setNome("Joao da Silva");
 * sindico.getPessoa().setGenero(Genero.MASCULINO);
 * sindico.getPessoa().setNascimento(LocalDate.now().withDayOfMonth(27).
 * withMonth(8).withYear(1978)); sindico.getPessoa().setCpf("745.936.370-74");
 * sindico.setDe(LocalDate.now().withDayOfMonth(1).withMonth(1));
 * sindico.setAte(LocalDate.now().withDayOfMonth(31).withMonth(12));
 * 
 * }
 * 
 * @Test public void pessoa() {
 * 
 * }
 * 
 * @Test public void morador() { Morador morador = new Morador();
 * morador.setApartamento(null); morador.setPessoa(new Pessoa());
 * morador.getPessoa().setCpf("999.999.800-99");
 * morador.getPessoa().setGenero(Genero.MASCULINO);
 * morador.getPessoa().setNascimento(LocalDate.now());
 * morador.getPessoa().setNome("Oi"); morador.getPessoa().setId(null);
 * morador.setProprietario(true); moradorRepository.saveAndFlush(morador);
 * log.info("{}", morador.getId()); }
 * 
 * }
 */