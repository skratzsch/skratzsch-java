# Build-Stage: Nutze Maven mit JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Kopiere nur pom.xml und lade Abhängigkeiten (Caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Kopiere den Sourcecode und baue das Projekt
COPY src ./src
RUN mvn package -DskipTests

# Runtime-Stage: Nutze JRE 17 und optimiere mit Layered JAR
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Kopiere das Layered JAR
COPY --from=build /app/target/*.jar app.jar

# Spring Boot Layered JAR extrahieren (für effizientes Caching)
RUN java -Djarmode=layertools -jar app.jar extract

# Port freigeben (Standard für Spring Boot: 8080)
EXPOSE 8080

# Anwendung starten
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
