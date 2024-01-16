ARG MAVEN_PROFILE=stage

# Build stage
FROM maven:3.8.6-jdk-11-slim AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Package stage
FROM openjdk:11-jre-slim
COPY --from=build /app/target/prometheus-integration-0.0.1-SNAPSHOT.jar /usr/local/lib/prometheus-integration.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/prometheus-integration.jar"]