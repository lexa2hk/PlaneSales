pipeline{
    agent{
        label "jenkins-agent1"
    }
    tools{
        jdk 'Java21'
        maven 'Maven3'
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
                withSonarQubeEnv(credentialsID: 'jenkins-sonarqube-token'){
                    sh "cd backend && mvn sonar:sonar -Dsonar.host.url=http://192.168.1.7:9000"
                }
                sh "cd backend && mvn soanr:sonar"
            }
        }
    }
}