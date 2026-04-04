# Research: Loan Mesh Learning App

## Decision: Use Kind for the local Kubernetes cluster

**Rationale**: Kind is lightweight, easy to recreate, and uses Docker directly,
which keeps setup friction low for a beginner learning loop on macOS.

**Alternatives considered**:
- Minikube: rejected because it adds more runtime setup variation and less
  predictable reset behavior for a local learning lab.

## Decision: Install Istio with the `demo` profile

**Rationale**: The `demo` profile exposes enough mesh functionality to support
traffic management, observability, and secure service boundaries without extra
manual control-plane tuning.

**Alternatives considered**:
- `minimal`: rejected because it omits too many learning aids and requires more
  follow-up configuration.
- `production`: rejected because stricter defaults add friction before the
  learner has seen the baseline behavior.

## Decision: Include Kiali, Prometheus, and Jaeger from the start

**Rationale**: The constitution requires visible proof for routing, protection,
and request flow changes. Kiali provides topology, Prometheus provides traffic
metrics, and Jaeger provides distributed trace visibility.

**Alternatives considered**:
- Metrics-only setup: rejected because it hides request topology and trace path.
- Tempo or other tracing stacks: rejected because Jaeger has the simplest local
  learning path with Istio sample addons.

## Decision: Model `loan-portal` as Angular frontend and keep two backend Spring Boot services

**Rationale**: This matches the user's stated architecture while preserving a
clear public entry point and a simple two-hop backend chain for service mesh
learning.

**Alternatives considered**:
- Three Spring Boot services: rejected because it would blur the difference
  between frontend and backend responsibilities.
- Single backend service: rejected because it removes the service-to-service
  hop needed for routing and protection exercises.

## Decision: Use synchronous REST between frontend and services

**Rationale**: Synchronous HTTP calls are easy to reason about, easy to verify
with `curl`, and easy to visualize in Kiali and Jaeger.

**Alternatives considered**:
- gRPC: rejected because it adds tooling complexity without improving the first
  set of learning outcomes.
- Event-driven messaging: rejected because it obscures the direct request path
  that beginners need to observe.

## Decision: Use H2 in-memory storage for each backend service

**Rationale**: H2 keeps the system disposable and local-first while still
supporting a realistic lifecycle for synthetic loan applications and decisions.

**Alternatives considered**:
- PostgreSQL/MySQL: rejected because external stateful dependencies add setup
  overhead unrelated to the mesh learning goal.
- No storage at all: rejected because the learner benefits from seeing a simple
  request state transition across services.

## Decision: Teach traffic management with weighted routing and subsets first

**Rationale**: Subset-based weighted routing demonstrates Istio's value clearly
without requiring application code changes. It maps directly to the second user
story and is visible in both repeated responses and Kiali.

**Alternatives considered**:
- Header-only routing: rejected as a later exercise because it is less intuitive
  for a first canary-style learning scenario.
- Fault injection first: rejected because the learner needs a healthy baseline
  path before studying failure behavior.