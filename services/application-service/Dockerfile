FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/application-service-*.jar application-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "application-service.jar"]