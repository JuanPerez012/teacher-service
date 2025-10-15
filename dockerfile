# ------ build ------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ------ run ------
FROM eclipse-temurin:17-jre-alpine
ENV TZ=America/Bogota
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app/app.jar"]
