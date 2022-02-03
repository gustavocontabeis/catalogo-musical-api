#Menu rápido:
Se já foi construido, apenas suba os serviços com

Desenvolvimento

~~~
docker container start condominio-adm-postgres
~~~

Produção

~~~
docker container start condominio-adm-nginx condominio-adm-postgres condominio-adm-pgadmin backend-catalogo-musical
~~~

a desça os serviços com

~~~
docker container stop condominio-adm-nginx condominio-adm-postgres condominio-adm-pgadmin backend-catalogo-musical
~~~

~~~
docker kill $(docker ps -q)
docker system prune --force -a
docker container ls -a
docker images
docker network ls
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
docker pull postgres:9.5-alpine
docker pull dpage/pgadmin4
~~~
##Rede

###Crie a rede para o postgres

~~~
docker network create --driver bridge postgres-network
docker network ls
~~~

~~~
docker run --name condominio-adm-postgres --network=postgres-network -e "POSTGRES_PASSWORD=Postgres2018!" -p 5432:5432 -v /home/gustavo/dev/workspace-coder/condominios-adm-api/data:/var/lib/postgresql/data -d postgres:9.5-alpine
~~~

~~~
docker container ps
~~~

~~~
PGADMIN
docker run --name condominio-adm-pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=gustavocontabeis@gmail.com" -e "PGADMIN_DEFAULT_PASSWORD=PgAdmin2018!" -d dpage/pgadmin4
~~~

~~~
http://localhost:15432
~~~

~~~
Login:
gustavocontabeis@gmail.com
Senha:
PgAdmin2018!
~~~

No PGAdmin Configure: 

~~~
	Add New Server
	General:
	Name:condominio-adm-postgres
	Connection:
	Host name/address:condominio-adm-postgres
	username: postgres
	Password: Postgres2018!
~~~

para rodar o backend fora do docker, pelo eclipse ou via console (java -jar) usa-se host = 0.0.0.0

para rodar o backend dentro do docker usa-se host = "nome do container do postgres"

Em uma aplicação com SpringBoot (pelo eclipse):

~~~
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://0.0.0.0:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=Postgres2018!
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
spring.jpa.hibernate.ddl-auto=create-drop
~~~

Pelo DBeaver use:

~~~
No DBeaver:
Host:0.0.0.0
database:postgres
user:postgres
Password:Postgres2018!
~~~

####Fontes
https://medium.com/@renato.groffe/postgresql-docker-executando-uma-inst%C3%A2ncia-e-o-pgadmin-4-a-partir-de-containers-ad783e85b1a4
https://medium.com/@fernandoevangelista_28291/criando-e-enviando-imagem-docker-com-java-e-maven-4fa3c70dba0f



####Limpando tudo 

~~~
gustavo@foguetinho:~$ sudo docker ps -a
gustavo@foguetinho:~$ sudo docker container stop condominio-adm-pgadmin
gustavo@foguetinho:~$ sudo docker container stop condominio-adm-postgres

gustavo@foguetinho:~$ sudo docker container rm condominio-adm-pgadmin
gustavo@foguetinho:~$ sudo docker container rm condominio-adm-postgres

gustavo@foguetinho:~$ sudo docker rmi postgres
gustavo@foguetinho:~$ sudo docker rmi dpage/pgadmin4
~~~





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

#####Não existe

builda o jar

~~~
	mvn clean package
~~~

builda a imagem

~~~
	docker build --tag=catalogo-musical-api:latest .
~~~

roda a imagem

~~~
	docker run --name backend-catalogo-musical -p 8084:8084 --network=postgres-network catalogo-musical-api:latest
~~~

#####Já existe
ou, caso tudo ja estaja buildado, apenas suba o container
	
~~~
	docker container start backend-catalogo-musical
~~~

####Limpando...
~~~
docker container rm backend-catalogo-musical
docker rmi catalogo-musical-api
~~~

###Executando a aplicação com o docker compose:

~~~
docker kill $(docker ps -q)
docker system prune --force -a
docker container ls -a
docker images
docker network ls
~~~

Verifique se o frontend foi construído:

~~~
cd /home/gustavo/dev/workspace-coder/catalogo-musical
npm install
ng build
~~~

Verifique se a imagem "catalogo-musical-api-compose" existe. 
Se não existe, builde-a com:

~~~
cd /home/gustavo/dev/workspace-coder/catalogo-musical-api
mvn clean package
docker build -f Dockerfile-compose-backend --tag=catalogo-musical-api-compose:latest .
docker images
~~~

Depois execute:

~~~
cd ~/dev/workspace-coder/catalogo-musical-api
docker-compose up
~~~

Agora teste com:

~~~
http://localhost
http://localhost:15432
gustavocontabeis@gmail.com
PgAdmin2018!
~~~

#Frontend 

~~~
cd ~/dev/workspace-coder/catalogo-musical 
ng build

docker run --name condominio-adm-nginx -p 80:80 -d -v /home/gustavo/dev/workspace-coder/catalogo-musical/dist/catalogo-musical:/usr/share/nginx/html nginx

ou 
docker container start condominio-adm-nginx
~~~

Acessar com: `http://localhost/`

docker container stop condominio-adm-nginx

