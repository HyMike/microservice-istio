# HTTP Interface Contracts: Loan Mesh Learning App

## Public Interface: Loan Portal Submission Flow

**Entry path**: Istio ingress gateway -> `loan-portal`

### Submit synthetic loan application

- **Method**: `POST`
- **Path**: `/api/loan-applications`
- **Purpose**: Accept a learner-submitted synthetic loan request and return the
  final learner-visible result.

**Request fields**:
- `applicantName`
- `loanAmount`
- `loanPurpose`
- `requestedTermMonths`

**Response fields**:
- `applicationId`
- `status`
- `decisionOutcome`
- `decisionSource`
- `decisionReason`

## Internal Interface: Application Service

### Create application review

- **Caller**: `loan-portal`
- **Method**: `POST`
- **Path**: `/internal/applications`
- **Purpose**: Convert a submitted loan application into an internal review
  record.

**Request fields**:
- `applicationId`
- `loanAmount`
- `loanPurpose`
- `requestedTermMonths`

**Response fields**:
- `reviewId`
- `reviewStatus`
- `normalizedAmount`

### Request underwriting decision

- **Caller**: `application-service`
- **Method**: `POST`
- **Path**: `/internal/applications/{applicationId}/decision`
- **Purpose**: Request a final underwriting decision for a submitted review.

**Request fields**:
- `applicationId`
- `reviewId`
- `normalizedAmount`
- `loanPurpose`

**Response fields**:
- `decisionId`
- `decisionOutcome`
- `decisionSource`
- `decisionReason`

## Internal Interface: Underwriting Service

### Produce underwriting decision

- **Caller**: `application-service`
- **Method**: `POST`
- **Path**: `/internal/underwriting-decisions`
- **Purpose**: Evaluate the synthetic request and return either the primary or
  alternate decision behavior.

**Request fields**:
- `applicationId`
- `reviewId`
- `normalizedAmount`
- `loanPurpose`

**Response fields**:
- `decisionId`
- `decisionOutcome`
- `decisionSource`
- `decisionReason`

## Contract Rules

- The public interface is the only learner-facing entry path.
- Internal interfaces are cluster-internal and not exposed as public entry
  points.
- `decisionSource` is required so the learner can verify weighted routing and
  alternate behavior.
- All payloads use synthetic, non-sensitive values only.

## Implementation Mapping (App-Only)

- Application service endpoint implementation path:
  `services/application-service/src/main/java/com/example/applicationservice/api/ApplicationController.java`
- Underwriting service endpoint implementation path:
  `services/underwriting-service/src/main/java/com/example/underwritingservice/api/UnderwritingController.java`
- Frontend API client path:
  `frontend/loan-portal/src/app/services/loan-api.service.ts`