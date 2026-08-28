# 🎬 BookMyShow DevOps CI/CD Project

A complete **DevOps CI/CD deployment project** for a BookMyShow backend application using **GitHub, Jenkins, Maven, Docker, Docker Hub, and AWS EC2**.

The project demonstrates how source code can automatically be built, containerized, pushed to Docker Hub, and deployed on an AWS EC2 server through a Jenkins Pipeline.

---

## 🚀 Project Overview

This project implements an automated CI/CD pipeline for the BookMyShow backend application.

### CI/CD Flow

```text
Developer
    │
    ▼
  GitHub
    │
    ▼
  Jenkins
    │
    ├── Checkout
    │
    ├── Maven Build
    │
    ├── Docker Build
    │
    ├── Docker Hub Push
    │
    ├── Docker Deploy
    │
    └── Deployment Verification
    │
    ▼
 AWS EC2
    │
    ├── BookMyShow Frontend :3000
    │
    └── BookMyShow Backend  :8080
```

---

# 🛠️ Technologies Used

| Technology   | Purpose                      |
| ------------ | ---------------------------- |
| Java 17      | Application compilation      |
| Spring Boot  | Backend application          |
| Maven 3.9.12 | Build automation             |
| Git          | Version control              |
| GitHub       | Source code repository       |
| Jenkins      | CI/CD automation             |
| Docker       | Application containerization |
| Docker Hub   | Docker image registry        |
| AWS EC2      | Cloud deployment             |
| Linux Ubuntu | Server operating system      |

---

# ☁️ AWS Infrastructure

The application is deployed on an AWS EC2 instance.

### EC2 Details

```text
Instance Type : t3.micro
Operating System : Ubuntu
Region : ap-south-1
Jenkins Port : 8081
Backend Port : 8080
Frontend Port : 3000
```

### Network

```text
Internet
    │
    ▼
AWS Internet Gateway
    │
    ▼
Public Subnet
    │
    ▼
EC2 Instance
    │
    ├── Jenkins :8081
    ├── Backend :8080
    └── Frontend :3000
```

---

# 🔄 CI/CD Pipeline

The Jenkins pipeline performs the following stages.

## 1. Checkout

Jenkins checks out the latest source code from GitHub.

```text
GitHub
   ↓
Jenkins Workspace
```

Repository:

```text
https://github.com/Divyyaasy/bookmyshow-devops.git
```

---

## 2. Maven Build

The application is compiled using Java 17.

```bash
mvn clean package -DskipTests
```

The generated JAR file is created inside:

```text
target/
```

---

## 3. Docker Build

Jenkins creates a Docker image from the application.

```text
sydivyyaa/bookmyshow-backend:${BUILD_NUMBER}
```

and:

```text
sydivyyaa/bookmyshow-backend:latest
```

---

## 4. Docker Hub Push

Jenkins securely logs into Docker Hub using Jenkins Credentials.

The images are pushed to:

```text
sydivyyaa/bookmyshow-backend
```

Two tags are pushed:

```text
BUILD_NUMBER
latest
```

---

## 5. Deployment

Jenkins pulls the latest Docker image and deploys it on EC2.

The old container is stopped and removed:

```bash
docker stop bookmyshow-backend
docker rm bookmyshow-backend
```

The new container is started:

```bash
docker run -d \
  --name bookmyshow-backend \
  -p 8080:8080 \
  sydivyyaa/bookmyshow-backend:latest
```

---

## 6. Deployment Verification

The pipeline verifies that the backend container is running and checks the application response.

```bash
docker ps
```

and:

```bash
curl http://localhost:8080/
```

---

# 🐳 Docker

### Docker Image

```text
sydivyyaa/bookmyshow-backend:latest
```

### Backend Container

```text
Container Name: bookmyshow-backend
Container Port: 8080
Host Port: 8080
```

### Frontend Container

```text
Container Name: bookmyshow-frontend
Container Port: 80
Host Port: 3000
```

---

# 🔐 Jenkins Credentials

Docker Hub credentials are stored securely inside Jenkins Credentials.

Credential ID:

```text
dockerhub-creds
```

The password/token is **not stored inside the Jenkinsfile**.

The pipeline uses:

```groovy
withCredentials([
    usernamePassword(
        credentialsId: 'dockerhub-creds',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )
])
```

---

# 📋 Jenkinsfile

The project contains a Jenkins Pipeline with the following stages:

```text
Checkout
    ↓
Maven Build
    ↓
Docker Build
    ↓
Docker Login & Push
    ↓
Deploy
    ↓
Verify
```

---

# 💻 Important Commands

### Check Jenkins

```bash
sudo systemctl status jenkins
```

### Check Jenkins port

```bash
sudo ss -lntp | grep java
```

### Check Docker

```bash
docker --version
```

### Check running containers

```bash
docker ps
```

### Check backend logs

```bash
docker logs bookmyshow-backend
```

### Check frontend logs

```bash
docker logs bookmyshow-frontend
```

### Test backend

```bash
curl http://localhost:8080/
```

---

# 🌐 Application URLs

### Jenkins

```text
http://<EC2-PUBLIC-IP>:8081
```

### BookMyShow Backend

```text
http://<EC2-PUBLIC-IP>:8080
```

### BookMyShow Frontend

```text
http://<EC2-PUBLIC-IP>:3000
```

> Replace `<EC2-PUBLIC-IP>` with the current EC2 public IP address.

---

# 📁 Project Structure

```text
bookmyshow-devops/
│
├── bookmyshow-backend/
│   │
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   ├── Dockerfile
│   └── Jenkinsfile
│
└── README.md
```

---

# 🔧 Jenkins Pipeline Environment

The pipeline uses Java 17 for compiling the application:

```text
JAVA17_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

Jenkins itself runs with Java 21, while the BookMyShow Maven project is compiled using Java 17.

---

# 📸 Project Demonstration Screenshots

The following screenshots can be included in the project documentation.

## Screenshot 1 — GitHub Repository

Show:

```text
GitHub Repository
bookmyshow-devops
```

---

## Screenshot 2 — Jenkins Dashboard

Show the Jenkins project:

```text
BookMyShow Pipeline
```

---

## Screenshot 3 — Successful Pipeline

Show all stages completed successfully:

```text
Checkout       ✓
Maven Build    ✓
Docker Build   ✓
Docker Push    ✓
Deploy         ✓
Verify         ✓
```

---

## Screenshot 4 — Jenkins Console Output

Show:

```text
BUILD SUCCESS
BOOKMYSHOW CI/CD DEPLOYMENT SUCCESSFUL
```

---

## Screenshot 5 — Docker Hub

Show:

```text
sydivyyaa/bookmyshow-backend
```

with:

```text
latest
BUILD_NUMBER
```

tags.

---

## Screenshot 6 — EC2 Docker Containers

Run:

```bash
docker ps
```

Show:

```text
bookmyshow-backend
bookmyshow-frontend
```

---

## Screenshot 7 — Application

Open the BookMyShow application in the browser and capture the working application.

---

# 🎯 Project Objectives

The main objectives of this project are:

* Automate application builds.
* Implement Continuous Integration.
* Implement Continuous Deployment.
* Containerize the application using Docker.
* Store Docker images in Docker Hub.
* Deploy the application on AWS EC2.
* Automate deployment using Jenkins.
* Reduce manual deployment effort.
* Demonstrate an end-to-end DevOps workflow.

---

# ✅ Project Result

The BookMyShow application is successfully integrated with a CI/CD pipeline.

Whenever updated source code is pushed to GitHub, Jenkins can:

```text
Pull Code
   ↓
Build Application
   ↓
Create Docker Image
   ↓
Push Image to Docker Hub
   ↓
Pull Latest Image
   ↓
Deploy Container
   ↓
Verify Application
```

This demonstrates an end-to-end **GitHub → Jenkins → Maven → Docker → Docker Hub → AWS EC2 CI/CD workflow**.

---

# 👩‍💻 Author

**Divyyaa S Yogananda**

BookMyShow DevOps CI/CD Deployment Project
