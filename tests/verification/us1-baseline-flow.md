# US1 Baseline Flow Verification

1. Start underwriting-service on port 8082.
2. Start application-service on port 8081.
3. Start or host the frontend loan portal.
4. Submit a valid payload with applicantName, loanAmount, loanPurpose, requestedTermMonths.
5. Verify response contains: applicationId, status, decisionOutcome, decisionSource, decisionReason.
6. Confirm request chain reaches both backend services via logs.
