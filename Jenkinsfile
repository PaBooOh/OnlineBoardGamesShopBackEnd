pipeline
{
    agent any

    tools {
        maven 'myMaven' // 这里是 Jenkins 中配置的 Maven 的名字
        jdk 'jdk11' // 这里是 Jenkins 中配置的 JDK 的名字
    }

    stages
    {
        stage('1. Pull Repo')
        {
            steps
            {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'c83f7749-7c53-40ec-b0f9-43ab5bea9ccb', url: 'https://github.com/PaBooOh/OnlineBoardGamesShopBackEnd.git']])
            }
        }

        stage('2. Build with Maven')
        {
            steps
            {
                sh 'mvn clean package'
            }
        }

        stage('3. Make docker images')
        {
            steps
            {
                sh '''mv ./target/*.jar.original .
                docker build -t myshop .'''
            }
        }
    }
}
