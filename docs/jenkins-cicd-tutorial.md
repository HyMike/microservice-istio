# Jenkins CI/CD Tutorial Plan for This Project

This guide is a follow-along plan for learning **Jenkins**, **CI/CD pipelines**, and eventually **Kubernetes deployment** using this repo.

---

## 🎯 Goal

By the end of this tutorial, you will be able to:

- run Jenkins locally on your Mac
- create a basic Jenkins pipeline for this repo
- build the Angular frontend and both Spring Boot services
- archive build artifacts in Jenkins
- extend the pipeline to build Docker images
- prepare the app for Kubernetes/Istio deployment from Jenkins

---

## 📦 Project Areas You Will Use

| Area | Path | Purpose |
|---|---|---|
| Frontend | `frontend/loan-portal` | Angular app build |
| App service | `services/application-service` | Spring Boot backend |
| Underwriting service | `services/underwriting-service` | Spring Boot backend |
| Kubernetes manifests | `k8s/` | Deployment targets |
| Local setup docs | `docs/local-prerequisites.md` | Prerequisite reference |
| Validation notes | `tests/verification/us1-baseline-flow.md` | End-to-end checks |

---

## ✅ Prerequisites

Before starting Jenkins, make sure your machine has:

- `git`
- Node.js 20+
- `npm`
- Java 21
- Maven 3.9+
- Docker Desktop
- optional later: `kubectl` and a local Kubernetes cluster

Helpful repo files:

- `scripts/setup-local.sh`
- `scripts/run-lab.sh`
- `docs/local-prerequisites.md`

---

## Phase 0 — Prepare the Lab

### Objective
Get Jenkins running locally so you can use it as your CI/CD server.

### Recommended approach
Use **Jenkins in Docker** on macOS.

### Suggested learning tasks
1. Start Jenkins locally.
2. Open the Jenkins UI.
3. Install suggested plugins.
4. Add the tools Jenkins will need:
   - Git
   - Node/npm
   - Java 21
   - Maven
   - later: Docker and kubectl

### Success check
You can open Jenkins in the browser and create a new Pipeline job.

---

## Phase 1 — Verify the Project Builds Manually

### Objective
Make sure the exact commands Jenkins will run already work outside Jenkins.

### Commands to practice

#### Frontend
```bash
cd frontend/loan-portal
npm install
npm run build
```

#### Application service
```bash
cd services/application-service
mvn clean package -DskipTests
```

#### Underwriting service
```bash
cd services/underwriting-service
mvn clean package -DskipTests
```

### Why this matters
If these commands do not work locally, Jenkins will fail too.

### Success check
You should end up with:
- Angular build output under `frontend/loan-portal/dist/`
- JAR files under each service's `target/` directory

---

## Phase 2 — Build Your First Jenkins CI Pipeline

### Objective
Create a beginner-friendly CI pipeline that only builds the application.

### First pipeline stages
1. `Checkout`
2. `Build Frontend`
3. `Build application-service`
4. `Build underwriting-service`
5. `Archive Artifacts`

### What Jenkins should do in this phase
- pull the repo from Git
- run the Angular build
- package both Spring Boot services
- save the output artifacts in Jenkins

### Artifact targets
- `frontend/loan-portal/dist/**`
- `services/application-service/target/*.jar`
- `services/underwriting-service/target/*.jar`

### Success check
A Jenkins run completes and shows the archived artifacts in the build output.

---

## Phase 3 — Improve the Pipeline for Learning

### Objective
Make the pipeline easier to read, debug, and reuse.

### Improvements to add
- reusable environment variables
- clean stage names
- timestamps and clearer logs
- optional post-build messages
- optional test stages later

### Good beginner focus
Keep the pipeline **simple and readable** before making it more advanced.

---

## Phase 4 — Add Docker Image Builds

### Objective
Extend CI so Jenkins also builds container images.

### Dockerfiles already available
- `services/application-service/Dockerfile`
- `services/underwriting-service/Dockerfile`

### Learning tasks
1. Build the image for `application-service`
2. Build the image for `underwriting-service`
3. Tag each image with something Jenkins provides, such as:
   - `BUILD_NUMBER`
   - Git commit SHA

### Success check
Running `docker images` shows the two service images with Jenkins-based tags.

---

## Phase 5 — Prepare the Repo for CD

### Objective
Make the Kubernetes deployment files usable from Jenkins.

### Important repo-specific issue
The current manifests use local-only image values such as:

- `loan/application-service:local`
- `loan/underwriting-service:local`
- `imagePullPolicy: Never`

That works for a local-only cluster, but not for a typical Jenkins-driven deployment.

### Files to review
- `k8s/application-deployment.yaml`
- `k8s/underwriting-deployment.yaml`
- `k8s/underwriting-deployment-v2.yaml`

### What will eventually need to change
- use registry-based image names
- use dynamic tags from Jenkins
- avoid hardcoded `:local` image references for CI/CD

---

## Phase 6 — Add Deployment Stages

### Objective
Turn the pipeline into CD by deploying to Kubernetes.

### Learning tasks
1. Give Jenkins access to `kubectl`
2. Make sure Jenkins can access your cluster
3. Deploy the manifests in `k8s/`
4. target the `loan-mesh` namespace

### Success check
After a Jenkins deployment run:
- the pods in `loan-mesh` start successfully
- the services are reachable
- the deployment reflects the image tag built by Jenkins

---

## Phase 7 — Verify the Application End-to-End

### Objective
Confirm the deployed system actually works.

### Verification references
- `tests/verification/us1-baseline-flow.md`
- `specs/001-loan-mesh-app/quickstart.md`

### What to verify
- frontend loads correctly
- application service responds
- underwriting service responds
- the baseline loan submission flow works end to end

---

## Phase 8 — Optional Advanced Topic: Istio / Canary Release

### Objective
Use Jenkins to learn more advanced deployment patterns.

### Advanced files
- `k8s/underwriting-deployment-v2.yaml`
- `k8s/underwriting-virtualservice.yaml`
- `k8s/istio-application-virtualservice.yaml`

### What you can explore later
- rolling out a second underwriting version
- traffic splitting with Istio
- controlled rollout ideas similar to canary deployment

> Save this phase for after the basic CI/CD loop is working.

---

## Recommended Learning Order

1. **Manual builds**
2. **Basic Jenkins CI**
3. **Artifact archiving**
4. **Docker image builds**
5. **Kubernetes deployment**
6. **Istio traffic management**

---

## Practical Milestones

### Milestone 1 — CI only
- Jenkins successfully builds all three app parts
- artifacts are archived

### Milestone 2 — CI + Docker
- Jenkins builds the Docker images
- tags are tied to Jenkins builds

### Milestone 3 — CI/CD
- Jenkins deploys updated images to Kubernetes
- app works after deployment

### Milestone 4 — Advanced rollout
- Jenkins supports alternate deployment strategies with Istio

---

## Notes for This Repo

- The Angular app lives in `frontend/loan-portal`.
- Both Java services use Spring Boot with Java 21.
- The current Kubernetes manifests are oriented toward local images.
- This makes the repo a good learning project because you can start small and expand gradually.

---

## Next Suggested Step

Start with **Phase 1** and confirm the manual build commands work on your machine.
Once that is stable, create a simple Jenkins Pipeline job and implement **Phase 2**.
