# Feature Specification: Loan Mesh Learning App

**Feature Branch**: `001-loan-mesh-app`  
**Created**: 2026-03-25  
**Status**: Draft  
**Input**: User description: "I would like to build a frontend app using angular and for the backend I want to build two microservices. Like this : \"loan-portal (instead of frontend) application-service (instead of orders) underwriting-service (instead of payments)\""

## User Scenarios & Testing *(mandatory)*

### User Story 1 - View End-to-End Loan Request Flow (Priority: P1)

As a learner, I want to submit a sample loan application through a visible user interface and have it travel through the internal services so that I can understand the baseline request path before adding advanced mesh features.

**Istio/Kubernetes Capability**: Ingress path visibility and service-to-service routing baseline

**Why this priority**: A working end-to-end request path is the minimum viable slice for any later Istio learning. Without a baseline flow, routing, security, and resiliency experiments have no reliable reference point.

**Independent Test**: Can be fully tested by opening the loan portal, submitting a sample application, and confirming that the request reaches the downstream services and returns a final status to the user.

**Verification Signal**: A successful user-facing response paired with visible evidence of the request path across the three services.

**Acceptance Scenarios**:
``
1. **Given** the learning environment is running, **When** a learner submits a valid sample loan request through the loan portal, **Then** the request is processed through the application service and underwriting service and a final result is shown to the learner.
2. **Given** the baseline flow is active, **When** the learner inspects the running environment, **Then** the externally reachable entry point and the internal service hops are clearly identifiable.

---

### User Story 2 - Practice Controlled Traffic Changes (Priority: P2)

As a learner, I want to introduce a second underwriting behavior and direct only part of the traffic to it so that I can observe how service mesh traffic policies change outcomes without changing the user journey.

**Istio/Kubernetes Capability**: Progressive traffic management

**Why this priority**: Controlled traffic shifting is one of the clearest demonstrations of service mesh value and builds directly on the baseline request path established in User Story 1.

**Independent Test**: Can be fully tested by sending repeated sample requests and confirming that some requests follow the alternate underwriting behavior while the rest continue to follow the primary behavior.

**Verification Signal**: Repeated requests show a measurable split between the primary and alternate underwriting outcomes.

**Acceptance Scenarios**:

1. **Given** an alternate underwriting behavior is available, **When** the learner applies a traffic rule, **Then** only the configured portion of requests follow the alternate behavior.
2. **Given** traffic is being split, **When** the learner removes the traffic rule, **Then** all requests return to the primary underwriting behavior.

---

### User Story 3 - Observe and Protect Internal Service Traffic (Priority: P3)

As a learner, I want to verify that internal loan-processing traffic is visible and protected so that I can understand how the mesh helps with troubleshooting and secure service boundaries.

**Istio/Kubernetes Capability**: Observability and service-to-service protection

**Why this priority**: Once the learner can see baseline flow and routing changes, the next step is understanding how the mesh exposes operational insight and enforces safer communication patterns.

**Independent Test**: Can be fully tested by generating sample requests, observing their path and timing, and confirming that internal service access remains protected while the public entry point stays available.

**Verification Signal**: The learner can inspect request flow evidence and confirm that only the intended public entry path is externally reachable.

**Acceptance Scenarios**:

1. **Given** the application is processing sample requests, **When** the learner inspects the environment, **Then** the path, timing, and result of the request can be observed across the services.
2. **Given** only the loan portal should be reachable from outside the mesh, **When** the learner attempts to access an internal service directly, **Then** that direct access is not available through the public entry path.

### Edge Cases

- What happens when the loan portal receives a sample request while one internal service is unavailable?
- How does the system handle an invalid or incomplete sample application submission?
- What happens when the alternate underwriting behavior is enabled but receives no traffic?

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The system MUST provide a learner-facing loan portal where a user can submit a synthetic loan application and view the resulting decision.
- **FR-002**: The system MUST process each submitted loan application through the application service before the request reaches the underwriting service.
- **FR-003**: The system MUST expose exactly one public entry flow for the learner-facing loan experience.
- **FR-004**: The system MUST support a primary underwriting behavior and an alternate underwriting behavior for traffic management exercises.
- **FR-005**: The system MUST allow the learner to observe whether a request followed the primary or alternate underwriting behavior.
- **FR-006**: The system MUST make the externally reachable path and the internal service hops visible to the learner.
- **FR-007**: The system MUST keep sample mortgage and banking data synthetic and free of real customer information.
- **FR-008**: The system MUST prevent internal-only service endpoints from being treated as learner-facing public entry points.

### Mesh Learning Requirements

- **MLR-001**: Each externally reachable user flow MUST identify the ingress path and internal service hops.
- **MLR-002**: Each story that changes mesh behavior MUST name the Istio resources or policies involved.
- **MLR-003**: Each story MUST define at least one observable verification signal.
- **MLR-004**: Sample data MUST remain synthetic and non-sensitive.

### Key Entities *(include if feature involves data)*

- **Loan Application**: A synthetic mortgage request submitted by the learner, including applicant summary data, loan amount, loan purpose, and decision status.
- **Application Review**: The intermediate processing state created by the application service before underwriting is requested.
- **Underwriting Decision**: The final or alternate decision outcome returned after internal evaluation of the sample application.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: A learner can complete a baseline sample loan submission from public entry to final decision in under 3 minutes using the quickstart flow.
- **SC-002**: In a guided traffic-splitting exercise, the learner can verify within 10 minutes that both primary and alternate underwriting behaviors are receiving requests according to the configured rule.
- **SC-003**: In at least 90% of guided practice runs, the learner can correctly identify the public entry point and the internal service sequence without external explanation.
- **SC-004**: The learning environment demonstrates that internal services are not exposed as public entry points while the learner-facing flow remains accessible.

## Assumptions

- Learners are primarily using the project to understand Kubernetes and Istio behavior rather than full mortgage business processes.
- Mobile and native client experiences are out of scope for the first version of this learning lab.
- The learning environment will run locally and use synthetic loan data only.
- The first version focuses on a small number of services so that routing, visibility, and protection concepts stay understandable.
