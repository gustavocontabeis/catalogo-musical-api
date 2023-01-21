package br.com.codersistemas.condominiosadm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import br.com.codersistemas.gem.exceptions.GemException;
import br.com.codersistemas.gem.gens.GemDebug;
import br.com.codersistemas.gem.gens.be.GemController;
import br.com.codersistemas.gem.gens.be.GemPojo;
import br.com.codersistemas.gem.gens.be.GemRepository;
import br.com.codersistemas.gem.gens.be.GemService;
import br.com.codersistemas.gem.gens.fe.AngularComponentFormHtml;
import br.com.codersistemas.gem.gens.fe.AngularComponentFormTS;
import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Attribute;
import br.com.codersistemas.gem.model.CamelCase;
import br.com.codersistemas.gem.model.Model;
import br.com.codersistemas.gem.model.Name;
import br.com.codersistemas.gem.model.Type;
import br.com.codersistemas.libs.utils.FileUtil;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class CoderGemTests {
	
	private Class[] classes = {};
	private App app;
	private int index = 4;
	private Model model;
	private java.util.Map<String, Object> input;
	
	@Before
	public void testBeforeEach(){
		System.out.println(">>> testBeforeEach");
		app = App.builder()
				.appName("condominios-adm-api")
				.defaultPackage("br.com.codersistemas.condominiosadm")
				.schema("public")
				.database("database")
				.models(new ArrayList<Model>())
				.build();
		
		app.addClasses(
				br.com.codersistemas.condominiosadm.domain.Apartamento.class,
				br.com.codersistemas.condominiosadm.domain.Banco.class,
				br.com.codersistemas.condominiosadm.domain.Banco2.class,
				br.com.codersistemas.condominiosadm.domain.BancoLancamento.class,
				br.com.codersistemas.condominiosadm.domain.Bloco.class,
				br.com.codersistemas.condominiosadm.domain.Boleto.class,
				br.com.codersistemas.condominiosadm.domain.Caixa.class,
				br.com.codersistemas.condominiosadm.domain.CentroDeCusto.class,
				br.com.codersistemas.condominiosadm.domain.Condominio.class,
				br.com.codersistemas.condominiosadm.domain.Faturamento.class,
				br.com.codersistemas.condominiosadm.domain.Morador.class,
				br.com.codersistemas.condominiosadm.domain.Pessoa.class,
				br.com.codersistemas.condominiosadm.domain.Sindico.class
			);
		
		model = app.getModels().get(index);
		input = new HashMap<String, Object>();
		input.put("title", "Exemplo Gem");
		input.put("hoje", new Date());
		input.put("app", app);
		input.put("models", app.getModels());
		input.put("model", model);
	}
	
	@Test
	public void testDebugModel() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		System.out.println("AppName:        "+app.getAppName());
		System.out.println("Database:       "+app.getDatabase());
		System.out.println("DefaultPackage: "+app.getDefaultPackage());
		System.out.println("Schema:         "+app.getSchema());
		List<Model> models = app.getModels();
		for (Model model : models) {
			System.out.println("");
			System.out.println("  model.label:                        "+model.getLabel());
			System.out.println("  model.restURI:                      "+model.getRestURI());
			System.out.println("  model.name:                         "+model.getName());
			System.out.println("  model.name.hifenCase:               "+model.getName().getHifenCase());
			System.out.println("  model.name.name:                    "+model.getName().getName());
			System.out.println("  model.name.underlineCase:           "+model.getName().getUnderlineCase());
			System.out.println("  model.name.camelCase.capitalized:   "+model.getName().getCamelCase().getCapitalized());
			System.out.println("  model.name.camelCase.uncapitalized: "+model.getName().getCamelCase().getUncapitalized());
			System.out.println("  model.pk.name.name:                 "+model.getPk().getName().getName());
			System.out.println("  model.pk.type.className:            "+model.getPk().getType().getClassName());
			System.out.println("  model.pk.type.simpleClassName:      "+model.getPk().getType().getSimpleClassName());
			System.out.println("");
			List<Attribute> attributes = model.getAttributes();
			for (Attribute attribute : attributes) {
				System.out.println("    attribute.columnName:                                    "+attribute.getColumnName());
				System.out.println("    attribute.label:                                         "+attribute.getLabel());
				System.out.println("    attribute.size:                                          "+attribute.getSize());
				System.out.println("    attribute.required:                                      "+attribute.isRequired());
				System.out.println("    attribute.isPk:                                          "+attribute.isPk());
				System.out.println("    attribute.isFk:                                          "+attribute.isFk());
				if(attribute.isFk() && !attribute.getType().isCollection()) {
				System.out.println("    attribute.type.fkModel:                                  "+attribute.getFkModel().getName());
				System.out.println("    attribute.fkModel.pk.type.className:                     "+attribute.getFkModel().getPk().getType().getClassName());
				System.out.println("    attribute.fkModel.pk.type.name.camelCase.uncapitalized:  "+attribute.getFkModel().getPk().getType().getName().getCamelCase().getUncapitalized());
				System.out.println("    attribute.fkModel.pk.type.simpleClassName:               "+attribute.getFkModel().getPk().getType().getSimpleClassName());
				}
				System.out.println("    attribute.name.hifenCase:                                "+attribute.getName().getHifenCase());
				System.out.println("    attribute.name.name:                                     "+attribute.getName().getName());
				System.out.println("    attribute.name.underlineCase:                            "+attribute.getName().getUnderlineCase());
				System.out.println("    attribute.name.camelCase.capitalized:                    "+attribute.getName().getCamelCase().getCapitalized());
				System.out.println("    attribute.name.camelCase.name:                           "+attribute.getName().getCamelCase().getName());
				System.out.println("    attribute.name.camelCase.uncapitalized:                  "+attribute.getName().getCamelCase().getUncapitalized());
				System.out.println("    attribute.type.className:                                "+attribute.getType().getClassName());
				System.out.println("    attribute.type.name.hifenCase:                           "+attribute.getType().getName().getHifenCase());
				System.out.println("    attribute.type.name.name:                                "+attribute.getType().getName().getName());
				System.out.println("    attribute.type.name.plural:                              "+attribute.getType().getName().getPlural());
				System.out.println("    attribute.type.name.camelCase.capitalized:               "+attribute.getType().getName().getCamelCase().getCapitalized());
				System.out.println("    attribute.type.name.camelCase.name:                      "+attribute.getType().getName().getCamelCase().getName());
				System.out.println("    attribute.type.name.camelCase.uncapitalized:             "+attribute.getType().getName().getCamelCase().getUncapitalized());
				System.out.println("    attribute.type.name.underlineCase:                       "+attribute.getType().getName().getUnderlineCase());
				System.out.println("    attribute.type.simpleClassName:                          "+attribute.getType().getSimpleClassName());
				System.out.println("    attribute.type.isCollection:                             "+attribute.getType().isCollection());
				System.out.println("    attribute.type.isPrimitive:                              "+attribute.getType().isPrimitive());
				System.out.println("    attribute.type.name:                                     "+attribute.getType().getName());
				System.out.println("    attribute.type.isEnum:                                   "+attribute.getType().isEnum());
				System.out.println("    attribute.type.getEnumValues:                            "+attribute.getType().getEnumValues());
				System.out.println("");
			}
		}
		
	}
	
	@Test
	public void testGemDebugTemplete() throws GemException {
		System.out.println("GemDebug");
		System.out.println(new GemDebug(app, model, input).print());
	}
	
	@Test
	public void testGerarPojo() throws GemException {
		System.out.println("GemPojo");
		System.out.println(new GemPojo(app, model, input).print());
	}
	
	@Test
	public void testGerarPojos() throws GemException {
		System.out.println("GemPojos");
		app.getModels().forEach(m->{try {
			FileUtil.write(new File("/home/gustavo/dev/workspace-coder/condominios-adm-api-copy/src/main/java/br/com/codersistemas/condominiosadm/domain", m.getName().getName()+".java"), new GemPojo(app, m, input).print());
		} catch (IOException | GemException e) {
			e.printStackTrace();
		}});
	}
	
	@Test
	public void testGerarDTO() throws GemException {
		
	}
	
	@Test
	public void testGerarAdapter() throws GemException {
		
	}

	@Test
	public void testGerarBuilder() throws GemException {
		
	}

	@Test
	public void testGerarDDLPostgres() throws GemException {
		
	}
	
	@Test
	public void testGerarSQLInserts() throws GemException {
		
	}

	@Test
	public void testGerarRepository() throws Exception {
		System.out.println("Repository");
		System.out.println(new GemRepository(app, model, input));
	}
	
	@Test
	public void testGerarRepositoryTest() {
		
	}
	
	@Test
	public void testGerarService() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		System.out.println("Service");
		System.out.println(new GemService(app, model, input));
	}
	
	@Test
	public void testGerarServiceTest() {
		
	}
	
	@Test
	public void testGerarRestController() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		System.out.println("Controller");
		System.out.println(new GemController(app, model, input));
	}
	
	@Test
	public void testGerarRestControllerTestesPostman() {
		// gerar URL
		// Gerar body post
	}

	@Test
	public void testGerarAngularCliCode() {
		
	}
	
	@Test
	public void testGerarTSClass() throws Exception {
		
	}
	
	@Test
	public void testGerarNGModule() throws Exception {
		
	}
	
	@Test
	public void testGerarNGService() throws Exception {
		
	}

	@Test
	public void testGerarAngularComponentFormHtml() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		System.out.println("AngularComponentFormHtml");
		System.out.println(new AngularComponentFormHtml(app, model, input));
	}
	
	@Test
	public void testGerarAngularComponentFormTS() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		System.out.println("AngularComponentFormTS");
		System.out.println(new AngularComponentFormTS(app, model, input));
	}

	@Test
	public void testGerarPrimeNGMenu() throws Exception {
		
	}
}
