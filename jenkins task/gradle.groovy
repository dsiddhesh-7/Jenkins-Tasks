pipeline {
    agent {
        label 'label1'
    }
    stages{
        stage('install gradle'){
            steps{
                sh '''
                sudo apt update
                sudo apt-get install gradle -y
                '''
            }
        }
        stage('pull a git file'){
            steps{
                git 'https://github.com/chinmay2512/studentapp-ui.git'
            }
        }
        stage('build code of gradle'){
            steps{
                sh 'gradle clean'
                sh 'gradle build'
                sh 'gradle test'
            }
        }
        stage('Downloading Tomcat 8'){
            steps{
                sh 'sudo wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.99/bin/apache-tomcat-8.5.99.tar.gz'
                sh 'sudo tar -xvf apache-tomcat-8.5.99.tar.gz'
            }
        }
        stage('Deploying war file'){
            steps{
                sh "  sudo  mv build/libs/*.war apache-tomcat-8.5.99/webapps/student.war"
            }
        }
        stage('Starting Apache 8'){
            steps{
                sh 'sudo ./apache-tomcat-8.5.99/bin/startup.sh'
            }
        }
    }    
}