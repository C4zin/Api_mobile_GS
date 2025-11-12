# Etapa 1: Build da aplicação com Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia os arquivos de configuração do Maven
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o JAR final
RUN mvn clean package -DskipTests
#weqweq
# Etapa 2: Imagem final mais leve só com o JAR
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado da etapa anterior (nome dinâmico)
COPY --from=build /app/target/*.jar app.jar

# Define a porta padrão usada pelo Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
