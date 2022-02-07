FROM openjdk:8-jdk-alpine
MAINTAINER codersistemas.com.br
COPY target/condominios-adm-api.jar condominios-adm-api.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","/condominios-adm-api.jar"]
