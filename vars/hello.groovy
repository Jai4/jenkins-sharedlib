def call(String name = 'human') {
   
   sh "docker create network --driver bridge united"
   def dockerRunOpts = "--network united --name ms -e GRADLE_USER_HOME=./.gradle"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-1.amazonaws.com/javagradlecurl:latest"
   sh "docker pull ${BUILD_IMAGE}"
   
   
   def localStackRunOpts = "--network united --name ls"
   def LOCALSTACK_IMAGE = "localstack/localstack"
   sh "docker pull ${LOCALSTACK_IMAGE}"
  
  
   docker.image(LOCALSTACK_IMAGE).withRun(localStackRunOpts){
      sleep 15
         docker.image(BUILD_IMAGE).inside(dockerRunOpts){
            sh "curl http://ls:4566/health"
        }
   }
}

