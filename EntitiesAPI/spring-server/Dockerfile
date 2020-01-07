# https://spring.io/guides/gs/spring-boot-docker/
FROM openjdk:8-jdk-alpine
LABEL version="1.0.0" authors="Nathanaël Mizutani, Olivier Koffi"
VOLUME [ "/tmp" ]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]