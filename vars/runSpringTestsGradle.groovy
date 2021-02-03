def call(Map args = [:]) {
    def useLocalStack  = args.useLocalStack == null ? false : args.useLocalStack
    
    pipeline {
    agent {
        label 'mainmachine'
    }
     stages {
         stage('Build MicroService') {
            steps {
                 script {
                        sh "echo printing ${useLocalStack}"
                        if(useLocalStack){
                            sh "i have entered the if condition"
                            runGradleBuild "hello"
                        } else {
                            //do something else
                        }
                 }
            }
        }
     }
  }
}