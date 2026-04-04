# Implementation Plan: Loan Mesh Learning App

**Branch**: `001-loan-mesh-app` | **Date**: 2026-03-25 | **Spec**: [spec.md](./spec.md)
**Input**: Feature specification from `/specs/001-loan-mesh-app/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/plan-template.md` for the execution workflow.

## Summary

Create a local-first Istio learning lab where an Angular loan portal submits
synthetic mortgage applications to two Spring Boot microservices,
`application-service` and `underwriting-service`, through an Istio-managed
mesh. The implementation will prioritize an observable baseline request path,
progressive traffic management experiments, and protected internal service
boundaries using a Kind cluster, the Istio demo profile, and Kiali,
Prometheus, and Jaeger for verification.

## Technical Context

**Frontend**: Angular 19 single-page application named `loan-portal`  
**Backend**: Spring Boot 3.4 microservices: `application-service` and `underwriting-service`  
**Languages**: TypeScript 5.x for frontend, Java 21 for backend services  
**Infrastructure**: Kind local cluster, Istio `demo` profile, Istio ingress gateway, Kiali, Prometheus, Jaeger  
**Storage**: H2 in-memory database per Spring Boot service; no external stateful dependencies in v1  
**Testing/Verification**: Manual verification with browser flow, `curl`, `kubectl`, `istioctl`, Kiali, Prometheus queries, and Jaeger traces; automated service tests optional later  
**Target Platform**: Local Kind cluster on macOS with Docker Desktop and `istioctl`  
**Project Type**: Web app plus microservices learning lab  
**Performance Goals**: Baseline request path visible end-to-end within one browser submission; weighted routing effects observable within 20 repeated requests; local setup usable on a developer laptop in under 30 minutes after prerequisites are installed  
**Constraints**: Local-first only, synthetic data only, exactly one public entry flow, internal services private by default, mesh-visible traffic required for all learning stories  
**Scale/Scope**: 1 Angular frontend, 2 backend microservices, 1 Kind cluster, 1 ingress gateway, beginner-friendly weekend scope

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- PASS: Stack aligns with Angular frontend, Spring Boot services, Kubernetes, and Istio.
- PASS: Scope remains a small, local, runnable increment focused on mesh learning.
- PASS: External traffic enters through the Istio ingress gateway to `loan-portal`.
- PASS: Each user story maps to explicit service hops, Istio resources, and observable signals.
- PASS: Internal services remain private by default; no public exposure beyond the loan portal is planned.
- PASS: Observability is included via Kiali, Prometheus, and Jaeger for routing and protection scenarios.

## Project Structure

### Documentation (this feature)

```text
specs/001-loan-mesh-app/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```text
frontend/
└── loan-portal/
  ├── src/
  ├── public/
  └── package.json

services/
├── application-service/
│   ├── src/main/
│   └── src/test/
└── underwriting-service/
  ├── src/main/
  └── src/test/

deploy/
├── k8s/
│   ├── base/
│   └── observability/
└── istio/
  ├── gateway/
  ├── routing/
  └── security/

tests/
├── integration/
└── verification/
```

**Structure Decision**: Use a separated frontend, services, deploy, and tests layout so learners can distinguish UI code, service code, Kubernetes manifests, and Istio policies without conflating responsibilities. `loan-portal` remains the only public entry application, while the backend services stay isolated under `services/` and are exposed only through in-cluster service discovery.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| None | N/A | N/A |

## Phase 0: Research Summary

- Local Kubernetes choice: Kind is preferred over Minikube for a lightweight, repeatable macOS learning loop.
- Istio installation: `demo` profile is selected to provide traffic, security, and observability capabilities with minimal setup friction.
- Observability stack: Kiali, Prometheus, and Jaeger are required to satisfy the constitution's proof-of-behavior rule.
- Service design: Angular `loan-portal` calls `application-service`, which calls `underwriting-service` over synchronous REST.
- Data/storage: H2 in-memory storage keeps the lab self-contained and disposable.
- Traffic learning path: weighted routing and subset-based traffic shifts are the first advanced mesh exercises.

## Phase 1: Design Summary

- Data model captures the synthetic loan application lifecycle and alternate underwriting outcomes.
- Contracts define one learner-facing submission flow and two internal service interfaces.
- Quickstart prioritizes local setup, verification commands, and manual checkpoints over automation.
- No constitutional violations were introduced during design; post-design constitution check remains PASS on all gates.
