# US3 Internal Boundary Checks

1. Verify frontend calls only `/api/loan-applications` on application-service.
2. Verify underwriting endpoint remains `/internal/underwriting-decisions`.
3. Verify no frontend route directly targets underwriting-service URL.
