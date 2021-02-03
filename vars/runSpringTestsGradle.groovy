def call(Map args = [:]) {
    def useLocalStack  = args.useLocalStack == null ? false : args.useLocalStack
    sh "echo printing ${useLocalStack}"

    pipeline {
    agent {
        label 'mainmachine'
    }
     stages {
         stage('Build MicroService') {
            steps {
                 script {
                        if(useLocalStack){
                            runGradleBuild 
                        } else {
                            //do something else
                        }
                 }
            }
        }
     }
  }
}