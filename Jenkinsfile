pipeline {
    agent any 

    tools {
        maven 'Maven 3.9.14'
        nodejs 'NodeJS 20.20.2'
    }
    
    environment {
        DOCKERHUB_REPO = "mhy88"
        APP_IMAGE = "${DOCKERHUB_REPO}/application-service"
        UW_IMAGE = "${DOCKERHUB_REPO}/underwriting-service"
        TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend/loan-portal') {
                sh 'npm install'
                sh 'npm run build'
                }
            }
        }

        stage('Build application-service') {
            steps {
                dir('services/application-service') {
                sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build underwriting-service') {
            steps {
                dir('services/underwriting-service') {
                sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t $APP_IMAGE:$TAG services/application-service'
                sh 'docker build -t $UW_IMAGE:$TAG services/underwriting-service'
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'f40d31f1-a53e-4116-9d13-96fd19950725',
                    usernameVariable: "DOCKERHUB_USERNAME",
                    passwordVariable: "DOCKERHUB_PASSWORD"
                )])
                {
                    sh 'echo "$DOCKERHUB_PASSWORD" | docker login -u "$DOCKERHUB_USERNAME" --password-stdin'
                    sh "docker push $APP_IMAGE:$TAG"
                    sh "docker push $UW_IMAGE:$TAG"

                }
            }
        }
    }
}