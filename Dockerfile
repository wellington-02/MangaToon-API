# Use uma imagem base do Maven para compilar a aplicação
FROM maven:3-eclipse-temurin-21-alpine AS build

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo pom.xml e as dependências do Maven
COPY pom.xml .

# Baixe as dependências do Maven
RUN mvn dependency:go-offline

# Copie o código-fonte da aplicação
COPY src ./src

# Compile a aplicação
RUN mvn clean package -DskipTests

# Efetivamente montando nossa imagem aqui com o jar gerado
FROM maven:3-eclipse-temurin-21-alpine

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo JAR da aplicação do estágio de build
COPY --from=build /app/target/MangaToon-API-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação irá rodar
EXPOSE 8080

# Defina o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]