# Sonetto Monorepo Bootstrap

## Struttura

- `be/`: backend Java con Quarkus + Mutiny.
- `fe/`: frontend Angular standalone con Signals e TypeScript strict.

## Avvio rapido

### Backend

```bash
cd be
mvn quarkus:dev
```

Endpoint di test:

- `GET http://localhost:8080/api/ping`

### Frontend

```bash
cd fe
npm install
npm start
```
