# Troubleshooting: Angular + Spring Boot Stack

## Frontend
- If imports fail, run `npm install` in `frontend/loan-portal`.
- If API calls fail, verify `applicationServiceBaseUrl` in `src/environments/environment.ts`.

## Backend
- If app service cannot call underwriting service, verify `underwriting.base-url` in `application.yml`.
- If H2 initialization fails, check SQL syntax in `data.sql` files.

## End-to-end
- Check service ports: app service 8081, underwriting service 8082.
- Validate request payload includes all required fields.
