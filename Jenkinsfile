pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6' // 这里是 Jenkins 中配置的 Maven 的名字
        jdk 'jdk11' // 这里是 Jenkins 中配置的 JDK 的名字
    }

    stages {
        stage('1. Pull Repo') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'c83f7749-7c53-40ec-b0f9-43ab5bea9ccb', url: 'https://github.com/PaBooOh/OnlineBoardGamesShopBackEnd.git']])
            }
        }

        stage('2. Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

//         stage('Build Docker image') {
//             steps {
//                 script {
//                     dockerImage = docker.build "username/myapp" // 将 "username/myapp" 替换为你的 Docker Hub 用户名和你的应用名
//                 }
//             }
//         }
//
//         stage('Push Docker Image') {
//             steps {
//                 script {
//                     docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
//                         dockerImage.push("my-tag")
//                     }
//                 }
//             }
        }
    }
}
