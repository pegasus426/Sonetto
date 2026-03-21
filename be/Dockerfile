# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn package -DskipTests

# Stage 2: Runtime (Fast-jar)
FROM eclipse-temurin:21-jre-jammy
ENV JAVA_APP_JAR=/deployments/quarkus-run.jar
COPY --from=build /usr/src/app/target/quarkus-app/ /deployments/
EXPOSE 8080
CMD ["java", "-jar", "/deployments/quarkus-run.jar"]