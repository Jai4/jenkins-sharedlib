def call(Map params = [:]) {
  
   // create custom bridge network
   sh "docker network create --driver bridge united"
   
   // set options for local stack container
   def localStackRunOpts = "--network united --name ls"
   def LOCALSTACK_IMAGE = "localstack/localstack"
   
  // set options for MicroService container
   def MicroServiceRunOpts = "--network united --name ms -e GRADLE_USER_HOME=./.gradle -e CONTAINER_NAME=ls"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-1.amazonaws.com/javagradlecurl:latest"

   // pull both images
   sh "docker pull ${LOCALSTACK_IMAGE}"
   sh "docker pull ${BUILD_IMAGE}"
  
   // Run the two containers as siblings
   try{
         docker.image(LOCALSTACK_IMAGE).withRun(localStackRunOpts){
               docker.image(BUILD_IMAGE).inside(MicroServiceRunOpts){
                  sh "./gradlew clean build --debug"
              }
         }
   } catch(Exception e){
       sh "echo error occured"
   } finally{
      sh "docker network rm united"   
   }     
}

