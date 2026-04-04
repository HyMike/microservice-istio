# Implementation Plan: [FEATURE]

**Branch**: `[###-feature-name]` | **Date**: [DATE] | **Spec**: [link]
**Input**: Feature specification from `/specs/[###-feature-name]/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/plan-template.md` for the execution workflow.

## Summary

[Extract from feature spec: primary requirement + technical approach from research]

## Technical Context

<!--
  ACTION REQUIRED: Replace the content in this section with the technical details
  for the project. The structure here is presented in advisory capacity to guide
  the iteration process.
-->

**Frontend**: [e.g., Angular 19 SPA or NEEDS CLARIFICATION]  
**Backend**: [e.g., Spring Boot 3.x services or NEEDS CLARIFICATION]  
**Infrastructure**: [e.g., Kubernetes, Istio, Kiali, Prometheus or NEEDS CLARIFICATION]  
**Storage**: [if applicable, e.g., PostgreSQL, files, or N/A]  
**Testing/Verification**: [e.g., JUnit, Cypress, curl scripts, Kiali, traces or NEEDS CLARIFICATION]  
**Target Platform**: [e.g., local Kind cluster on macOS/Linux or NEEDS CLARIFICATION]
**Project Type**: [e.g., web app + microservices learning lab or NEEDS CLARIFICATION]  
**Performance Goals**: [domain-specific, e.g., visible request routing under local load or NEEDS CLARIFICATION]  
**Constraints**: [domain-specific, e.g., local-first, synthetic data only, mesh-visible traffic or NEEDS CLARIFICATION]  
**Scale/Scope**: [domain-specific, e.g., 3 services, 1 Angular frontend, 1 local cluster or NEEDS CLARIFICATION]

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- Stack aligns with Angular frontend, Spring Boot services, Kubernetes, and Istio.
- Scope teaches a concrete mesh or Kubernetes capability in a small, runnable increment.
- External traffic enters through an Istio-managed gateway or the plan documents an explicit exception.
- Each user story defines the service hop, Istio resource or policy, and observable verification signal.
- Internal services remain private by default and any auth or exposure change is explicitly justified.
- Observability coverage exists for routing, resiliency, and security changes.

## Project Structure

### Documentation (this feature)

```text
specs/[###-feature]/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)
<!--
  ACTION REQUIRED: Replace the placeholder tree below with the concrete layout
  for this feature. Expand the chosen structure with real paths. The delivered
  plan must not leave sample names in place.
-->

```text
frontend/
└── [Angular application structure]

services/
├── [service-name]/
│   ├── src/main/
│   └── src/test/
└── [service-name]/

deploy/
├── k8s/
└── istio/

tests/
├── integration/
└── verification/
```

**Structure Decision**: [Document the selected structure and reference the real directories captured above]

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| [e.g., 4th project] | [current need] | [why 3 projects insufficient] |
| [e.g., Repository pattern] | [specific problem] | [why direct DB access insufficient] |
