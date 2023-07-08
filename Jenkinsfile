pipeline
{
    agent any

    tools {
        maven 'myMaven' // Maven path configured in Jenkins
        jdk 'jdk11' // jdk path configured in Jenkins
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
                    sh 'docker tag myshop:latest $DOCKER_USER/myshop:latest'
                    sh 'docker push $DOCKER_USER/myshop:latest'
                }
            }
        }
        stage('5. Create deployment script')
        {
            steps
            {
                withCredentials([usernamePassword(credentialsId: 'DOCKER_HUB_CREDS', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh """
                        echo 'docker login -u $DOCKER_USER -p $DOCKER_PASSWORD' > deploy.sh
                        echo 'docker stop onlineshopbackend' >> deploy.sh
                        echo 'docker pull $DOCKER_USER/myshop:latest' >> deploy.sh
                        echo 'docker run --rm -d -p 8964:9999 --name onlineshopbackend $DOCKER_USER/myshop:latest' >> deploy.sh
                        echo 'docker image prune -f' >> deploy.sh
                    """
                }
            }
        }
        stage('6. Deploy to Azure Virtual Machine')
        {
            steps
            {
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: 'AzureVMSSH',  // Azure name configured in Jenkins
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: 'sh /home/azureuser/devopspipeline/deploy.sh',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '[, ]+',
                                    remoteDirectory: '',
                                    remoteDirectorySDF: false,
                                    removePrefix: '',
                                    sourceFiles: 'deploy.sh'
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}
