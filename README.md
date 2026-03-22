# Sonetto

Sonetto is a poetry IDE and tooling platform for modern poets.

It helps users write poems following different metrical structures, supports multiple languages, runs AI-powered metrical analysis, and provides intuitive interfaces to save and evolve their work.

Built for "new Dante" creators, Sonetto offers live rhyme and meter suggestions while writing.

## Why Sonetto

- Write with guidance instead of static text editing.
- Compose across multiple poetic traditions and languages.
- Get real-time suggestions for rhyme and metric patterns.
- Receive AI-enhanced feedback on structure and flow.
- Keep poems organized with a creator-friendly workspace.

## Live Sonetto Demo

[https://sonetto.onrender.com/]

##  SwaggerUI

[https://sonettobe.onrender.com/q/swagger-ui/]
[http://localhost:8080/q/swagger-ui/]



## Core Features

- **Poetry-first editor** with live poetic constraints.
- **Metric schemas** for structured composition workflows.
- **Multilingual support** for cross-language poetry creation.
- **AI-powered metrical analysis** for richer technical feedback.
- **Live rhyme and meter hints** during composition.
- **Poem persistence and project organization** through intuitive UI flows.

## Monorepo Structure

- `be/` - Reactive backend (Java, Quarkus, Mutiny).
- `fe/` - Frontend application (Angular, standalone components, strict TypeScript).

## Quick Start

### Backend (Quarkus + Mutiny)

```bash
cd be
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
cp .env.example .env
# opzionale: aggiorna .env con provider/API key reali
set -a; source .env; set +a
mvn quarkus:dev
```

Backend default endpoint:

- `GET http://localhost:8080/api/ping`

### Frontend (Angular)

```bash
cd fe
npm install
npm start
```

## Open Source

Sonetto is open source under the MIT License.

- License text: see `LICENSE`
- Contributor workflow and engineering standards: see `CONTRIBUTING.md`

## Vision

Sonetto aims to become the developer-grade environment for poetry:

- as practical as an IDE,
- as expressive as a notebook,
- and as supportive as a writing coach.


## Dev Joy Mode

```bash
cd ./be
rm -rf ~/.m2/wrapper/dists/apache-maven-3.9.6-bin
./mvnw -v
./mvnw quarkus:dev
```

## Backend Environment in Docker

The backend Docker image (`be/Dockerfile`) does not hardcode LLM credentials.
Pass runtime environment variables when starting the container:

```bash
docker build -t sonetto-be ./be
docker run --rm -p 8080:8080 \
  -e OPENAI_COMPAT_API_KEY=change-me \
  -e OPENAI_COMPAT_BASE_URL=http://host.docker.internal:11434/v1 \
  -e OPENAI_COMPAT_MODEL=llama3-70b-8192 \
  sonetto-be
```

Notes:
- For Linux, if `host.docker.internal` is unavailable, use your host IP.
- These variables map directly to `be/src/main/resources/application.properties`.

## How to Run Tests (Arturo's Commands)

### Daily Development

`./mvnw test`

Result: Runs all tests, but skips tests tagged as `integration`. Groq usage stays under control.

### Manual Integration Tests

`./mvnw test -Pit`

Result: Runs only tests that call Groq. Useful to verify that prompts and API integration still work.
