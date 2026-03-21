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