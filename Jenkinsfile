pipeline{
    agent{
        label "jenkins-agent1"
    }
    tools{
        jdk 'Java21'
        maven 'Maven3'
    }
    environment{
        APP_NAME = "planesales"
        RELEASE = "1.0.0"
        DOCKER_USER = "oauth"
        DOCKER_PASS = credentials('yc-oauth')
        DOCKER_IMAGE = "${APP_NAME}"
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
                    docker.withRegistry("https://cr.yandex/crp81nvosn3h45fo9pqf", 'yc-oauth'){
                        def dockerImage = docker.build("${DOCKER_IMAGE}","backend/")
                        dockerImage.push("${IMAGE_TAG}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }
}