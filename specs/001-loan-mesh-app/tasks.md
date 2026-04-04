# Tasks: Loan Mesh Learning App

**Input**: Design documents from `/specs/001-loan-mesh-app/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Automated test tasks are intentionally omitted because the feature specification did not explicitly request TDD. Story-level validation tasks are included and required.

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

**Learner-Owned Rule**: Kubernetes and Istio wiring are intentionally out of scope in this file. The learner will author and validate all Kubernetes and Istio resources separately.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Initialize Angular and Spring Boot codebases plus local developer prerequisites

- [X] T001 Create project directory skeleton in frontend/loan-portal/, services/application-service/, services/underwriting-service/, and tests/verification/
- [X] T002 Initialize Angular app workspace in frontend/loan-portal/package.json and frontend/loan-portal/angular.json
- [X] T003 Initialize Spring Boot project for application service in services/application-service/pom.xml and services/application-service/src/main/java/
- [X] T004 Initialize Spring Boot project for underwriting service in services/underwriting-service/pom.xml and services/underwriting-service/src/main/java/
- [X] T005 [P] Add local setup helper scripts and prerequisites doc in scripts/setup-local.sh and docs/local-prerequisites.md

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core baseline required before implementing any user story

**CRITICAL**: No user story work starts until this phase is complete

- [X] T006 Define shared request and response contract models in contracts/http-interfaces.md and frontend/loan-portal/src/app/models/loan-models.ts
- [X] T007 [P] Configure synthetic seed data and H2 startup scripts in services/application-service/src/main/resources/data.sql and services/underwriting-service/src/main/resources/data.sql
- [X] T008 [P] Add shared error response and validation handling in services/application-service/src/main/java/com/example/applicationservice/api/ErrorHandler.java and services/underwriting-service/src/main/java/com/example/underwritingservice/api/ErrorHandler.java
- [X] T009 Create local execution profiles and ports for all apps in frontend/loan-portal/src/environments/environment.ts, services/application-service/src/main/resources/application.yml, and services/underwriting-service/src/main/resources/application.yml
- [X] T010 Add foundational verification runbook for local API-to-API flow in tests/verification/foundation-readiness.md

**Checkpoint**: Foundation ready - user story implementation can begin

---

## Phase 3: User Story 1 - View End-to-End Loan Request Flow (Priority: P1) MVP

**Goal**: Submit a synthetic loan application from loan-portal and receive final decision through both backend services

**Independent Test**: Run all three apps locally, submit one valid loan application from the portal, and verify end-to-end response through both backend services

- [X] T011 [P] [US1] Implement loan submission form and result view in frontend/loan-portal/src/app/features/loan-submit/loan-submit.component.ts and frontend/loan-portal/src/app/features/loan-submit/loan-submit.component.html
- [X] T012 [P] [US1] Implement Angular API client for submission flow in frontend/loan-portal/src/app/services/loan-api.service.ts
- [X] T013 [US1] Implement application-service endpoint to create application reviews in services/application-service/src/main/java/com/example/applicationservice/api/ApplicationController.java
- [X] T014 [US1] Implement application-service call to underwriting-service in services/application-service/src/main/java/com/example/applicationservice/service/UnderwritingClient.java
- [X] T015 [US1] Implement underwriting decision endpoint in services/underwriting-service/src/main/java/com/example/underwritingservice/api/UnderwritingController.java
- [X] T016 [US1] Add response DTO mapping for learner-facing result in services/application-service/src/main/java/com/example/applicationservice/api/dto/SubmissionResultDto.java
- [X] T017 [US1] Add baseline end-to-end verification steps and expected evidence in tests/verification/us1-baseline-flow.md

**Checkpoint**: User Story 1 fully functional and independently testable

---

## Phase 4: User Story 2 - Practice Controlled Traffic Changes (Priority: P2)

**Goal**: Introduce alternate underwriting behavior and verify weighted traffic split

**Independent Test**: Run repeated submissions and confirm both primary and alternate underwriting code paths are hit according to application-level switch conditions

- [X] T018 [P] [US2] Implement alternate underwriting behavior with decisionSource tagging in services/underwriting-service/src/main/java/com/example/underwritingservice/service/DecisionService.java
- [X] T019 [US2] Add request-level routing selector input to application-service in services/application-service/src/main/java/com/example/applicationservice/service/UnderwritingClient.java
- [X] T020 [US2] Surface decisionSource in learner-facing response UI in frontend/loan-portal/src/app/features/loan-submit/loan-result.component.html
- [X] T021 [US2] Add application-level traffic-split simulation configuration in services/application-service/src/main/resources/application.yml
- [X] T022 [US2] Add alternate-path verification script and expected distribution checks in tests/verification/us2-alternate-path.md

**Checkpoint**: User Stories 1 and 2 work independently and together

---

## Phase 5: User Story 3 - Observe and Protect Internal Service Traffic (Priority: P3)

**Goal**: Make request path observable and enforce internal service boundary protections

**Independent Test**: Generate sample traffic, confirm structured logs and request correlation across services, and verify service APIs only expose intended endpoints

- [X] T023 [P] [US3] Add structured request logging with correlation fields in services/application-service/src/main/java/com/example/applicationservice/config/LoggingConfig.java
- [X] T024 [P] [US3] Add structured request logging with correlation fields in services/underwriting-service/src/main/java/com/example/underwritingservice/config/LoggingConfig.java
- [X] T025 [US3] Add correlation-id propagation from frontend to backend in frontend/loan-portal/src/app/interceptors/correlation-id.interceptor.ts
- [X] T026 [US3] Restrict and document public vs internal API exposure in services/application-service/src/main/java/com/example/applicationservice/api/ and services/underwriting-service/src/main/java/com/example/underwritingservice/api/
- [X] T027 [US3] Add observability verification instructions using service logs in tests/verification/us3-observability-checks.md
- [X] T028 [US3] Add internal endpoint access verification checklist in tests/verification/us3-internal-boundary-checks.md

**Checkpoint**: All user stories are independently functional and verifiable

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Final consistency, documentation, and reproducibility improvements

- [X] T029 [P] Update end-to-end learner walkthrough for local app-only run in specs/001-loan-mesh-app/quickstart.md
- [X] T030 [P] Add troubleshooting guide for Angular and Spring Boot local failures in docs/troubleshooting-app-stack.md
- [X] T031 Consolidate run commands for frontend and both services in scripts/run-lab.sh
- [X] T032 Reconcile contract and data-model terminology with implementation docs in specs/001-loan-mesh-app/contracts/http-interfaces.md and specs/001-loan-mesh-app/data-model.md
- [X] T033 Re-run full validation checklist and capture evidence links in tests/verification/final-validation-report.md

---

## Dependencies & Execution Order

### Phase Dependencies

- **Phase 1 (Setup)**: No dependencies; start immediately
- **Phase 2 (Foundational)**: Depends on Phase 1 completion; blocks all stories
- **Phase 3 (US1)**: Depends on Phase 2 completion
- **Phase 4 (US2)**: Depends on Phase 3 baseline flow availability
- **Phase 5 (US3)**: Depends on Phase 3 baseline flow availability
- **Phase 6 (Polish)**: Depends on all selected user stories being complete

### User Story Dependencies

- **US1 (P1)**: Starts after Foundational phase; no dependency on other stories
- **US2 (P2)**: Starts after US1 baseline is available
- **US3 (P3)**: Starts after US1 baseline is available

### Parallel Opportunities

- Setup: T005 can run in parallel with T002-T004
- Foundational: T007 and T008 can run in parallel after T006
- US1: T011 and T012 can run in parallel; backend tasks follow service dependencies
- US2: T018 can run in parallel with frontend update T020
- US3: T023 and T024 can run in parallel before verification tasks
- Polish: T029 and T030 can run in parallel

---

## Parallel Execution Examples

### User Story 1

```bash
# Parallel frontend work
Task T011
Task T012
```

### User Story 2

```bash
# Parallel preparation for traffic-splitting exercise
Task T018
Task T020
```

### User Story 3

```bash
# Parallel service observability instrumentation
Task T023
Task T024
```

---

## Implementation Strategy

### MVP First (US1 only)

1. Complete Phase 1 Setup
2. Complete Phase 2 Foundational
3. Complete Phase 3 User Story 1
4. Validate baseline flow with T017
5. Demo baseline before adding advanced mesh behavior

### Incremental Delivery

1. Deliver baseline end-to-end flow (US1)
2. Add controlled traffic management (US2)
3. Add observability and boundary protections (US3)
4. Finalize docs and reproducibility steps in Polish phase

### Team Parallel Strategy

1. One contributor owns frontend tasks in `frontend/loan-portal/`
2. One contributor owns backend service tasks in `services/`
3. One contributor owns verification/docs tasks in `tests/verification/`, `docs/`, and `scripts/`
4. Merge at each story checkpoint with independent validation evidence

---

## Notes

- Every task follows the required checklist format with ID and file path
- Story labels appear only in user-story phases
- [P] markers are only assigned to tasks with non-overlapping file dependencies
- Validation tasks are mandatory for each user story to enforce observable outcomes
- Kubernetes and Istio implementation are intentionally excluded from this tasks file and handled manually by the learner outside this scope
