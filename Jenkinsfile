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
        DOCKER_USER = "oauth"
        DOCKER_PASS = credentials('yc-oauth')
        DOCKER_IMAGE = "${APP_NAME}"
        IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
        YC_REG_ID = credentials('yc-registry-id')
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
                    docker.withRegistry("cr.yandex/crpcuq9ts77lf7h4mkj2", 'yc-oauth'){
                        def dockerImage = docker.build("${DOCKER_IMAGE}","-f backend/Dockerfile .")
                        dockerImage.push("${IMAGE_TAG}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }
}