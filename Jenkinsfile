



pipeline {
    agent any
    
    tools {
        jdk 'jdk11'
        maven 'maven3'
    }
    
    environment {
        SCANNER_HOME=tool 'sonar-scanner'
    }

    stages {
        stage('Git Checkout') {
            steps {
               git branch: 'main', changelog: false, poll: false, url: 'https://github.com/GITHUBREVADI/EKart.git'
            }
        }
            stage('Compile') {
            	steps {
            		sh "mvn clean compile"
            	}
            }
            
            stage('SonarQube Anallysis') {
            	steps {
            		sh """
            		${SCANNER_HOME}/bin/sonar-scanner \
            		-Dsonar.host.url=http://40.67.172.33:9000 \
            		-Dsonar.login=squ_bbebd2ab54ab318f25b4352d6b16524693aa7114 \
            		-Dsonar.projectKey=Ekart \
            		-Dsonar.ProjectName=Ekart \
            		-Dsonar.sources=. \
            		-Dsonar.java.binaries=target/classes
            		"""
            	}
}

                    stage('Build Application') {
                    	steps {
                    		sh 'mvn clean package -DskipTests'
                    	}
                    }
                    
                            
                    stage('Build & Push Docker Image') {
                    	steps {
                    		script
                    		{
                    		    withDockerRegistry(credentialsId: '1a851612-ca58-4c66-b6d6-637d4851a750') {
                                sh "docker build -t ekart123:latest -f docker/Dockerfile ."
                                sh "docker tag ekart123:latest dockerlabs4srinu/ekart123:latest"
                                sh "docker push dockerlabs4srinu/ekart123:latest"
                                }
                    		}
                    	}
                    }
                    
            stage('Trigger CD Pipeline') {
            	steps {
            		build job: "CD_Pipeline" ,wait:true
            	}
            }
    }
}
