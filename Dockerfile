FROM openjdk:8-jdk-alpine
MAINTAINER codersistemas.com.br
COPY target/catalogo-musical-api.jar catalogo-musical-api.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=kubernetes", "-jar","/catalogo-musical-api.jar"]