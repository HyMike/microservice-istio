# Foundation Readiness

## Checks
1. Frontend dependencies install with `npm install` in `frontend/loan-portal`.
2. Maven dependencies resolve in both services with `mvn -q -DskipTests package`.
3. Application service can call underwriting service on local ports.
4. Synthetic sample payload returns a non-error response path.
