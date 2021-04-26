package br.com.codersistemas.catalogomusical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
public class CatalogoMusicalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoMusicalApiApplication.class, args);
	}

}

/* - spring boot war 
@SpringBootApplication
//@EnableJpaRepositories("br.com.codersistemas.catalogomusical")
//@ComponentScan ({"br.com.codersistemas.catalogomusical.repository"})
public class CatalogoMusicalApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoMusicalApiApplication.class, args);
	}

}
*/