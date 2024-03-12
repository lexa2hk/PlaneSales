pipeline{
    agent{
        label "jenkins-agent1"
    }
    tools{
        jdk 'Java21'
        maven 'Maven3'
    }
    environment{
        APP_NAME = "PlaneSales"
        RELEASE = "1.0.0"
        DOCKER_USER = "lexa2hk"
        DOCKER_PASS = credentials('dockerhub-pass')
        DOCKER_IMAGE = "${DOCKER_USER}/${APP_NAME}"
        IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
    }
    stages{
        stage("Workspace Cleanup"){
            steps{
                cleanWs()
            }
        }
        stage("Checkout from SCM"){
            steps{
                git branch: 'master',  credentialsId: 'GitHub', url: 'https://github.com/lexa2hk/PlaneSales/'
            }
        }
        stage("Build Application"){
            steps{
                sh "cd backend && mvn clean package"
            }
        }
        stage("Test Application"){
            steps{
                sh "cd backend && mvn test"
            }
        }
        stage("SonarQube Static Analysis"){
            steps{
                script{
                    withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token'){
                        sh "cd backend && mvn sonar:sonar"
                    }
                }
            }
        }
        stage("Quality Gate"){
            steps{
                script{
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonarqube-token'
                }
            }
        }
        stage("Build & Push DockerHub Image"){
            steps{
                script{
                    docker.withRegistry('',DOCKER_PASS){
                        dockerImage = docker.build("${DOCKER_IMAGE}","-f backend/Dockerfile .")
                    }

                    docker.withRegistry('https://index.docker.io/v1/',DOCKER_PASS){
                        dockerImage.push("${IMAGE_TAG}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }
}