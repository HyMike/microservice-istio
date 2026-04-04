# Quickstart: Loan Mesh Learning App (App-Only Track)

## Goal

Run the Angular loan portal and both Spring Boot microservices locally,
validate end-to-end request flow, and prepare a clean application baseline
before manual Kubernetes and Istio wiring.

## Prerequisites

- Node.js 20+
- JDK 21
- Maven 3.9+
- Optional: Angular CLI

## 1. Install dependencies

1. Frontend: `cd frontend/loan-portal && npm install`
2. Application service: `cd services/application-service && mvn -q -DskipTests package`
3. Underwriting service: `cd services/underwriting-service && mvn -q -DskipTests package`

## 2. Run services locally

1. Start underwriting service (port 8082).
2. Start application service (port 8081).
3. Start frontend loan portal.

## 3. Verify baseline flow

1. Submit a synthetic loan application from the portal.
2. Confirm response includes: `applicationId`, `status`, `decisionOutcome`,
   `decisionSource`, and `decisionReason`.
3. Confirm application service logs show call-out to underwriting service.
4. Confirm correlation headers are present in both service logs.

## 4. Verify alternate path behavior

1. Submit repeated requests using `routePreference: auto`.
2. Confirm both `decisionSource=primary` and `decisionSource=alternate`
   appear across responses.
3. Force `routePreference: primary` and `routePreference: alternate` and
   verify deterministic behavior.

## Expected Outcome

At the end of this quickstart, the learner has a working app-only baseline for
manual Kubernetes and Istio integration.