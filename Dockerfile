# Estágio de build
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

# Copie o arquivo pom.xml e faça o download das dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie todo o código-fonte e compile a aplicação
COPY src ./src
RUN mvn package

# Estágio de produção
FROM openjdk:11-jre-slim
WORKDIR /app

# Copie o arquivo .jar construído no estágio anterior
COPY --from=build /app/target/*.jar .

# Expõe a porta em que a aplicação Spring Boot será executada
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
#ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005","-jar", "mudi-0.0.1-SNAPSHOT.jar"]
CMD  ["java", "-jar", "mudi-0.0.1-SNAPSHOT.jar"]
#CMD ["sleep", "infinity"]