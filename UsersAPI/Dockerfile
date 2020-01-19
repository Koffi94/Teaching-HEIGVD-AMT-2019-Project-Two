FROM openjdk:11-jdk-slim

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY spring-server/target/user-api-1.0.0.jar /usr/src/user-api.jar

WORKDIR /usr/src

CMD ["/wait-for-it.sh" , "db:3306" , "--strict" , "--timeout=0" , "--" , "java", "-jar", "user-api.jar"]
