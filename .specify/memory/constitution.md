<!--
Sync Impact Report
Version change: template -> 1.0.0
Modified principles:
- Template Principle 1 -> I. Learning-First Scope
- Template Principle 2 -> II. Mesh-Visible Architecture
- Template Principle 3 -> III. Verification Before Expansion
- Template Principle 4 -> IV. Secure-by-Default Service Boundaries
- Template Principle 5 -> V. Observability Is Part of the Feature
Added sections:
- Technology Boundaries
- Workflow & Quality Gates
Removed sections:
- None
Templates requiring updates:
- UPDATED .specify/templates/plan-template.md
- UPDATED .specify/templates/spec-template.md
- UPDATED .specify/templates/tasks-template.md
- PENDING .specify/templates/commands/*.md (directory not present in this workspace)
Follow-up TODOs:
- None
-->
# Istio Learning Lab Constitution

## Core Principles

### I. Learning-First Scope
Every feature MUST teach a specific Istio or Kubernetes capability through a
small, runnable increment. Work MUST stay scoped so a beginner can set it up
locally, observe the behavior change in one sitting, and roll it back without
data loss. Features that do not strengthen understanding of Angular client
integration, Spring Boot service behavior, Kubernetes operations, or Istio mesh
behavior are out of scope unless they remove a direct blocker. Rationale: this
repository exists to learn the platform stack, not to simulate a full banking
product.

### II. Mesh-Visible Architecture
The application MUST use Angular for the user-facing client, Spring Boot for
backend services, and Kubernetes with Istio as the runtime control plane.
Backend capabilities MUST be split into independently deployable services with
named service-to-service hops, and all external traffic MUST enter through an
Istio-managed gateway or equivalent ingress path. Direct pod-to-pod shortcuts,
shared in-process mocks, or bypassing the mesh for convenience are prohibited
unless documented as a temporary learning exception in the plan. Rationale:
Istio is only meaningful when traffic boundaries and service edges are visible.

### III. Verification Before Expansion
Each user story MUST define the request path being exercised, the Istio
resources expected to affect it, and the concrete verification method.
Verification MUST include at least one observable signal such as curl output,
HTTP status or headers, a Kiali graph change, a Prometheus metric, or a trace
span. Infrastructure, routing, resiliency, or security work is not complete
until the expected before and after behavior is demonstrated. Rationale: mesh
concepts remain abstract unless every change has a proof point.

### IV. Secure-by-Default Service Boundaries
Internal services MUST default to private, mutually authenticated communication
inside the mesh. Public exposure MUST be limited to explicitly selected entry
points, and authentication and authorization requirements for sensitive routes
MUST be specified before implementation. Sample data MUST remain synthetic and
non-sensitive; no real customer, mortgage, or banking data may be introduced.
Rationale: learning service mesh without enforcing boundaries teaches the wrong
operational habits.

### V. Observability Is Part of the Feature
Every service added to the lab MUST emit enough telemetry to explain request
flow, latency, and failures. Plans and tasks MUST include logging, metrics, and
trace verification for any story that changes routing, resiliency, or security
behavior. If a behavior cannot be observed, it is not sufficiently implemented.
Rationale: Istio's value is operational visibility as much as traffic control.

## Technology Boundaries

- The canonical stack for this repository is Angular frontend, Spring Boot
	services, Kubernetes manifests, and Istio configuration.
- Local-first development MUST be supported. Any feature requiring cloud-only
	infrastructure MUST be deferred or replaced with a local equivalent.
- Service domain examples MAY use mortgage or banking concepts, but business
	logic MUST remain intentionally thin; the lab optimizes for platform learning,
	not financial domain completeness.
- Repository structure MUST separate application code from deployment artifacts
	so learners can distinguish code behavior from mesh policy behavior.

## Workflow & Quality Gates

- Every spec MUST contain independently testable learner-facing user stories and
	state the Istio capability each story teaches.
- Every plan MUST pass a constitution check that confirms stack alignment,
	gateway exposure rules, verification signals, and observability coverage.
- Every tasks document MUST include tasks for deployment manifests, Istio
	policy or configuration, and validation steps for each user story.
- Changes that add a new service, public route, or security policy MUST include
	updated quickstart or runbook instructions.
- Complexity exceptions MUST be recorded in the plan's Complexity Tracking
	section before implementation starts.

## Governance

This constitution overrides conflicting repository guidance for this project.
Amendments MUST document the changed principle, the reason for the change, the
impact on templates, and any migration steps for existing specs or plans.
Versioning follows semantic versioning for governance: MAJOR for removing or
materially redefining a principle, MINOR for adding a principle or materially
expanding required practice, and PATCH for clarifications that do not change
required behavior. Compliance review is mandatory at spec creation, plan
approval, and task generation; non-compliant artifacts MUST be corrected before
implementation. The ratification date records the first adoption of this
constitution; the last amended date MUST be updated whenever constitutional
content changes.

**Version**: 1.0.0 | **Ratified**: 2026-03-25 | **Last Amended**: 2026-03-25
