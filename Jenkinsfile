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
                sh '''cp ./target/myShop.jar .
                docker build -t myshop .
                docker image prune -f'''
            }
        }

        stage('4. Push image to DockerHub')
        {
            steps
            {
                withCredentials([usernamePassword(credentialsId: 'DOCKER_HUB_CREDS', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')])
                {
                    sh 'docker login -u $DOCKER_USER -p $DOCKER_PASSWORD'
                    sh 'docker push couping/myshop:latest'
                }
            }
        }

//         stage('5. Production environment deployment')
//         {
//             steps
//             {
//                 script
//                 {
//                     sshagent(credentials: ['AZURE_SSH_CREDS'])
//                     {
//                         sh """
//                             ssh -o StrictHostKeyChecking=no azureuser@20.126.86.227 << EOF
//                                 docker login -u couping -p Nc480sdsltyyz!
//                                 docker pull couping/myshop:latest
//                                 docker stop onlineshop
//                                 docker rm onlineshop
//                                 docker run -d -p 8964:9999 --name onlineshop couping/myshop:latest
//                             EOF
//                         """
//                     }
//                 }
//             }
//         }

    }
}
