def call(String name = 'human') {
   
   def dockerRunOpts = "--network host -e GRADLE_USER_HOME=./.gradle"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:latest"
   sh "docker pull 507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:latest"
   
   
   def localStackRunOpts = "--network host"
   def LOCALSTACK_IMAGE = "localstack/localstack"
   sh "docker pull localstack/localstack"
  
  
   docker.image(LOCALSTACK_IMAGE).withRun(localStackRunOpts){
         docker.image(BUILD_IMAGE).inside(dockerRunOpts){
            sh "./gradlew clean build"
        }
   }
}

