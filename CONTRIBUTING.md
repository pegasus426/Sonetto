# Contributing to Sonetto

Thanks for helping build Sonetto, the poetry IDE.

This project is open source under the MIT License and welcomes contributions from developers, poets, and AI/UX enthusiasts.

## Development Philosophy

- Keep the user experience centered on poetry workflows.
- Prefer simple, composable architecture over clever complexity.
- Protect code quality through strict typing, clear boundaries, and automated checks.
- Keep frontend and backend contracts aligned.

## Technical Stack

- **Backend (`be/`)**: Java + Quarkus + Mutiny (reactive-first).
- **Frontend (`fe/`)**: Angular (standalone architecture) + strict TypeScript.

## Local Setup

### Requirements

- Java 21+
- Maven 3.9+
- Node.js 20+ (or current LTS)
- npm 10+

### Run Backend

```bash
cd be
mvn quarkus:dev
```

### Run Frontend

```bash
cd fe
npm install
npm start
```

## Trunk-Based Development Guidelines

We follow a trunk-based workflow with `main` as the single integration branch.

- Branch from `main` using short-lived branches.
- Keep branches small and focused (one concern per branch).
- Rebase frequently on top of `main`.
- Open pull requests early and keep them reviewable.
- Merge fast after approval and passing checks.
- Avoid long-running feature branches.

Recommended branch naming:

- `feat/<short-description>`
- `fix/<short-description>`
- `chore/<short-description>`
- `docs/<short-description>`

## Commit Guidelines

- Use clear, imperative commit messages.
- Explain the intent (why), not only the diff (what).
- Keep commits atomic and cohesive.

Examples:

- `add live rhyme suggestion service`
- `fix meter parser for hendecasyllabic lines`
- `update frontend DTOs for analysis response`

## Pull Request Guidelines

- Link relevant issue(s), if any.
- Explain problem, approach, and impact.
- Include test notes and screenshots for UI changes.
- Call out API/DTO changes explicitly.
- Ensure no secrets or credentials are committed.

## Code Quality Expectations

- Follow SOLID principles.
- Keep strict TypeScript typing (no `any`).
- Backend must remain reactive-first and non-blocking.
- Prefer constructor injection patterns.
- Add tests for meaningful behavior changes.

## Cross-Team Contract Discipline

If a backend DTO or JSON field changes:

- update frontend interfaces/types in the same PR,
- document breaking changes,
- and verify end-to-end behavior.

## License

By contributing, you agree that your contributions are licensed under the MIT License of this repository.
