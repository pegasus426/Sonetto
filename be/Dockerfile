# Stage 1: Build Native (Usiamo Mandrel per Java 21)
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-21 AS build
COPY --chown=quarkus:quarkus . /code/
USER quarkus
WORKDIR /code

# Costruisce l'eseguibile nativo usando il wrapper mvnw incluso nel progetto
RUN ./mvnw package -Dnative -DskipTests

# Stage 2: Runtime (Micro-image minimale)
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY --from=build /code/target/*-runner /work/application

# Permessi e configurazione runtime
RUN chmod 775 /work
EXPOSE 8080
USER 1001

# L'host 0.0.0.0 è fondamentale per far vedere l'app a Railway/Render
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]