	cd C:\Program Files\PostgreSQL\9.6\bin>
	psql -U postgres
	create database bd_catalogo_musical_api with owner=postgres encoding='utf8';
	template1=# create user usr_catalogo_musical_api with password 'pwd_catalogo_musical_api';
	CREATE ROLE
	template1=# grant ALL on DATABASE bd_catalogo_musical_api to usr_catalogo_musical_api;
	GRANT
	template1=# \q	
	
	psql -U usr_catalogo_musical_api -d bd_catalogo_musical_api
	pwd_catalogo_musical_api
	
	\connect bd_catalogo_musical_api
	
	\q
	
	--enviando o dump
	scp /c/home/teste1.sql coder1@codersistemas.com.br:~
	
	--logando na vm
	ssh coder1@codersistemas.com.br (pwd /home/coder1)
	
	
	--executando o dump
	psql -U usr_catalogo_musical_api -d bd_catalogo_musical_api -f /home/coder1/teste1.sql bd_catalogo_musical_api
	pwd_catalogo_musical_api
	
	
	psql -U usr_catalogo_musical_api -d bd_catalogo_musical_api
	pwd_catalogo_musical_api
	
	--verificando os schemas
	\dn
	
	--verificando as tabelas
	\dt
	
	
	--agora é...
	--Gerar o build no perfil de PRD (sem create-table) 
	
	
	
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_catalogo_musical_api
spring.datasource.username=usr_catalogo_musical_api
spring.datasource.password=pwd_catalogo_musical_api
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=FALSE




grant ALL on schema public to usr_catalogo_musical_api;






-----------------------------------------------------------
--                  GERAR ARTEFATO
-----------------------------------------------------------





-----------------------------------------------------------
--                  GERAR ARTEFATO
-----------------------------------------------------------

	API:
	
		IMPORTANTE: Colocar no pom o executable e finalname. se alterar o nome do jar vai dar problema...
		
		cd /c/home/gustavo/desenv/workspace-github/coder-gem-api
		cd /home/gustavo/dev/workspace-coder/coder-gem-api/
		
		colar o prod.properties...
		
		mvn package -DskipTests
		ls target/ -lah
		cd target
		scp coder-gem-api.jar coder1@codersistemas.com.br:~
		ssh coder1@codersistemas.com.br
		sudo rm /etc/init.d/coder-gem-api
		sudo rm /var/apps/coder-gem-api/coder-gem-api.jar
		sudo mv coder-gem-api.jar /var/apps/coder-gem-api/
		ls -lah /var/apps/coder-gem-api/
		java -jar /var/apps/coder-gem-api/coder-gem-api.jar
			http://codersistemas.com.br:8084/coder-gem/pessoas 
			http://localhost:8084/coder-gem/pessoas
		
		
		via init.d (System V - antigo)

			sudo ln -s /var/apps/coder-gem-api/coder-gem-api.jar /etc/init.d/coder-gem-api
	
			sudo update-rc.d coder-gem-api defaults
			service coder-gem-api start
			service coder-gem-api status
	
			/etc/init.d/coder-gem-api stop
			/etc/init.d/coder-gem-api start
			http://192.168.1.119:8085/coder-gem-api/pessoas
			
		via systemd

			scp /home/gustavo/dev/workspace-coder/coder-gem-api/infra/coder-gem-api.service coder1@codersistemas.com.br:~
			ssh coder1@codersistemas.com.br
			sudo cp coder-gem-api.service /etc/systemd/system 
			#executar para atualizar
			systemctl daemon-reload
			
			service coder-gem-api stop
			service coder-gem-api start
			service coder-gem-api status
		
		
		service coder-gem-api stop && service coder-gem-api start && service coder-gem-api status
		
		
		
		mvn package -DskipTests -Dspring.profiles.active=prod 
		mvn clean package -Pprod -DskipTests
		
		java -jar target/coder-gem-api-0.0.1-SNAPSHOT.jar

		
 deploy frontend
 
 	#preparando o ambiente:
	  	ssh coder1@codersistemas.com.br
	 	cd /var/www
	 	ls -lah
	 	sudo rm -r *.*
	 	sudo rm -r coder-gem-ui
		exit
	 
 	replace enviroment -> url ->  pela de produção
 	
 	cd /c/home/gustavo/desenv/workspace-github/coder-gem-ui/
 	cd ~/dev/workspace-coder/coder-gem-ui/
 	ng build --prod
 	cd dist/
 	ls -lah
 	tar -cf coder-gem-ui.tar .
 	scp  coder-gem-ui.tar coder1@codersistemas.com.br:~
 	ssh coder1@codersistemas.com.br 
 	ls -lah
 	sudo cp ~/coder-gem-ui.tar /var/www/
 	cd /var/www/
 	ls -lah
 	sudo tar -xf coder-gem-ui.tar
 	ls coder-gem-ui -lah
 	cd coder-gem-ui 
	sudo cp *.* ..
	cd ..
 	sudo rm coder-gem-ui.tar
	ls -lah
	
	service nginx restart
	
	http://codersistemas.com.br/

 	
	
	ls /etc/nginx/sites-enabled/ -lah
	sudo ln -s /etc/nginx/sites-available/nginx-default.conf /etc/nginx/sites-enabled/
