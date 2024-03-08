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
    }
}