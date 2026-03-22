# Stage 1: Build Native (Usa l'immagine ufficiale di GraalVM/Quarkus)
FROM quay.io/quarkus/ubi-quarkus-graalvm-ce-builder-image:23.0.3-java21 AS build
COPY --chown=quarkus:quarkus . /code/
USER quarkus
WORKDIR /code
# Costruisce l'eseguibile nativo (AOT compilation)
RUN ./mvnw package -Dnative -DskipTests

# Stage 2: Runtime (Usa un'immagine minimale "distroless" o micro-linux)
# Non serve più la JRE perché il binario è auto-eseguibile!
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY --from=build /code/target/*-runner /work/application

# Permessi per l'esecuzione
RUN chmod 775 /work
EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]