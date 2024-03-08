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
                git branch: 'master',  credentialsId: 'GitHub', url: 'https://github.com/leha2hk/PlaneSales'
            }
        }
        // stage("Build"){
            
        // }
    }
}