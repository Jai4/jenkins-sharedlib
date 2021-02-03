def call(String name = 'human') {
  
   sh "docker network create --driver bridge united"
   
   def localStackRunOpts = "--network united --name ls"
   def LOCALSTACK_IMAGE = "localstack/localstack"
   sh "docker pull ${LOCALSTACK_IMAGE}"
   
  
   def dockerRunOpts = "--network united --name ms -e GRADLE_USER_HOME=./.gradle -e CONTAINER_NAME=ls"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-1.amazonaws.com/javagradlecurl:latest"
   sh "docker pull ${BUILD_IMAGE}"
  
   try{
         docker.image(LOCALSTACK_IMAGE).withRun(localStackRunOpts){
               docker.image(BUILD_IMAGE).inside(dockerRunOpts){
                  sh "./gradlew clean build"
              }
         }
   } catch(Exception e){
       sh "echo error occured"
   } finally{
      sh "docker network rm united"   
   }     
}

