#!/usr/bin/env bash
set -euo pipefail

echo "Run frontend (placeholder):"
echo "  cd frontend/loan-portal && npm install && npm run start"

echo "Run application-service:"
echo "  cd services/application-service && mvn spring-boot:run"

echo "Run underwriting-service:"
echo "  cd services/underwriting-service && mvn spring-boot:run"
