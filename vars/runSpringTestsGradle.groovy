def call(Map args = [:]) {
    def useLocalStack  = args.useLocalStack == null ? false : args.useLocalStack
    
    pipeline {
    agent {
        label 'mainmachine'
    }
     stages {
         stage('Build MicroService') {
            steps {
                    runGradleBuild "hello"   
                 }
            }
        }
     }
}
