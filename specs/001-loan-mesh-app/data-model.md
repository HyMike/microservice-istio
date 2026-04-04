# Data Model: Loan Mesh Learning App

## Entity: Loan Application

**Purpose**: Represents a synthetic mortgage request created by the learner.

**Fields**:
- `applicationId`: unique identifier
- `applicantName`: synthetic display name
- `loanAmount`: requested amount
- `loanPurpose`: purchase, refinance, or equity scenario
- `requestedTermMonths`: repayment term
- `status`: draft, submitted, under-review, decided, failed
- `submittedAt`: submission timestamp

**Validation rules**:
- Applicant name must be present.
- Loan amount must be greater than zero.
- Loan purpose must be one of the supported synthetic scenarios.
- Requested term must be a positive whole number.

**State transitions**:
- `draft` -> `submitted` when the learner submits the form.
- `submitted` -> `under-review` when `application-service` accepts the request.
- `under-review` -> `decided` when `underwriting-service` returns a decision.
- Any active state -> `failed` when downstream processing cannot complete.

## Entity: Application Review

**Purpose**: Captures the intermediate processing work performed by
`application-service` before underwriting.

**Fields**:
- `reviewId`: unique identifier
- `applicationId`: reference to loan application
- `normalizedAmount`: adjusted amount used for downstream evaluation
- `reviewNotes`: synthetic explanation for training visibility
- `reviewStatus`: queued, evaluating, forwarded, failed
- `reviewedAt`: processing timestamp

**Relationships**:
- Each `Application Review` belongs to one `Loan Application`.
- Each `Loan Application` may have one active `Application Review` in v1.

**Validation rules**:
- `applicationId` must reference an existing submitted application.
- `reviewStatus` must follow the allowed transition sequence.

## Entity: Underwriting Decision

**Purpose**: Represents the output produced by `underwriting-service`, including
alternate behavior used for traffic-splitting exercises.

**Fields**:
- `decisionId`: unique identifier
- `applicationId`: reference to loan application
- `decisionOutcome`: approved, conditionally-approved, denied
- `decisionSource`: primary or alternate underwriting path
- `decisionReason`: synthetic explanation shown to the learner
- `decidedAt`: decision timestamp

**Relationships**:
- Each `Underwriting Decision` belongs to one `Loan Application`.
- One `Loan Application` produces one visible decision in v1.

**Validation rules**:
- `decisionSource` must match one of the supported traffic-managed variants.
- `decisionOutcome` must map to a supported learner-facing result.

## Derived View: Learner Submission Result

**Purpose**: Aggregates the final information displayed back to the learner in
the Angular loan portal.

**Fields**:
- `applicationId`
- `status`
- `decisionOutcome`
- `decisionSource`
- `decisionReason`

**Usage**:
- Returned after end-to-end processing completes.
- Used for manual verification during routing and observability exercises.

## Implementation Mapping (App-Only)

- Frontend model types:
	`frontend/loan-portal/src/app/models/loan-models.ts`
- Submission response DTO:
	`services/application-service/src/main/java/com/example/applicationservice/api/dto/SubmissionResultDto.java`
- Alternate decision logic source:
	`services/underwriting-service/src/main/java/com/example/underwritingservice/service/DecisionService.java`