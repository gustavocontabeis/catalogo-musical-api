#Menu rápido:
Se já foi construido, apenas suba os serviços com

~~~
docker container start teste-nginx teste-postgres teste-pgadmin backend-catalogo-musical
~~~
a desça os serviços com

~~~
docker container stop teste-nginx teste-postgres teste-pgadmin backend-catalogo-musical
~~~

~~~
docker system prune --force -a
docker container ls -a
docker images
~~~

#Rodando o Postgres via Docker

####Parar o postgres atual

~~~
sudo service postgresql status
sudo service postgresql stop
sudo service postgresql status
~~~

###Listar as imagens:
~~~
sudo docker images
~~~
###Se não houver a imagen do postgres, baixe ela
~~~
docker pull postgres
docker pull dpage/pgadmin4
~~~
##Rede

###Crie a rede para o postgres
~~~
docker network create --driver bridge postgres-network
docker network ls
~~~
~~~
sudo docker run --name teste-postgres --network=postgres-network -e "POSTGRES_PASSWORD=Postgres2018!" -p 5432:5432 -v /home/gustavo/dev/CursoDocker/exemplo-postgres -d postgres:9.5-alpine
~~~

~~~
docker container ps
~~~

~~~
PGADMIN
docker run --name teste-pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=gustavocontabeis@gmail.com" -e "PGADMIN_DEFAULT_PASSWORD=PgAdmin2018!" -d dpage/pgadmin4
~~~

~~~
http://localhost:15432
~~~

Login:
gustavocontabeis@gmail.com
Senha:
PgAdmin2018!

No PGAdmin Configure: 
	Add New Server
	General:
	Name:teste-postgres
	Connection:
	Host name/address:teste-postgres
	username: postgres
	Password: Postgres2018!

para rodar o backend fora do docker, pelo eclipse ou via console (java -jar) usa-se host = 0.0.0.0
para rodar o backend dentro do docker usa-se host = "nome do continer do postgres"

Em uma aplicação com SpringBoot (pelo eclipse):
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://0.0.0.0:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=Postgres2018!
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
spring.jpa.hibernate.ddl-auto=create-drop


No DBeaver:
Host:0.0.0.0
database:postgres
user:postgres
Password:Postgres2018!

####Fontes
https://medium.com/@renato.groffe/postgresql-docker-executando-uma-inst%C3%A2ncia-e-o-pgadmin-4-a-partir-de-containers-ad783e85b1a4
https://medium.com/@fernandoevangelista_28291/criando-e-enviando-imagem-docker-com-java-e-maven-4fa3c70dba0f



####Limpando tudo 
gustavo@foguetinho:~$ sudo docker ps -a
gustavo@foguetinho:~$ sudo docker container stop teste-pgadmin
gustavo@foguetinho:~$ sudo docker container stop teste-postgres

gustavo@foguetinho:~$ sudo docker container rm teste-pgadmin
gustavo@foguetinho:~$ sudo docker container rm teste-postgres

gustavo@foguetinho:~$ sudo docker rmi postgres
gustavo@foguetinho:~$ sudo docker rmi dpage/pgadmin4






docker tag local-image:tagname new-repo:tagname
sudo docker tag catalogo-musical-api:0.0.1-SNAPSHOT new-repo:0.0.1-SNAPSHOT


docker push new-repo:tagname

docker push gustavocontabeis/catalogo-musical-api:0.0.1-SNAPSHOT

docker run -p 8080:8080 gustavocontabeis/catalogo-musical-api:0.0.1-SNAPSHOT


####Fonte
~~~
https://medium.com/@fernandoevangelista_28291/criando-e-enviando-imagem-docker-com-java-e-maven-4fa3c70dba0f
~~~

#Backend
cd /home/gustavo/dev/workspace-coder/catalogo-musical-api

para rodar o backend fora do docker usa-se host = 0.0.0.0
para rodar o backend dentro do docker usa-se host = "nome do container do postgres"

###Executando o backend sem o docker:
~~~
	mvn clean package
	java -jar -Dspring.profiles.active=dev target/catalogo-musical-api.jar
~~~

###Executando o backend com o docker:
~~~
	mvn clean package
	docker build --tag=catalogo-musical-api:latest .
	docker run --name backend-catalogo-musical -p 8084:8084 --network=postgres-network catalogo-musical-api:latest
~~~
	ou
	
~~~
	docker container start backend-catalogo-musical
~~~

####Limpando...
~~~
docker container rm backend-catalogo-musical
docker rmi catalogo-musical-api
~~~

###Executando a aplicaçção com o docker compose:

Verifique se o frontend foi construído:

~~~
cd /home/gustavo/dev/workspace-coder/catalogo-musical
npm install
ng build
~~~

Verifique se a imagem "catalogo-musical-api-compose" existe. 
Se não existe, builde-a com:

~~~
mvn clean package
docker build -f Dockerfile-compose-backend --tag=catalogo-musical-api-compose:latest .
~~~

Depois execute:

~~~
cd ~/dev/workspace-coder/catalogo-musical-api
docker-compose up
~~~

Agora teste com:

~~~
http://localhost
~~~

#Frontend 

~~~
cd ~/dev/workspace-coder/catalogo-musical 
ng build

docker run --name teste-nginx -p 80:80 -d -v /home/gustavo/dev/workspace-coder/catalogo-musical/dist/catalogo-musical:/usr/share/nginx/html nginx

ou 
docker container start teste-nginx
~~~

Acessar com: `http://localhost/`

docker container stop teste-nginx

