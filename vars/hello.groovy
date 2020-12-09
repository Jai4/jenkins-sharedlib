def call(String name = 'human') {
   echo "Hello, ${name}."
   echo "Present working directory of my jenkins build is: " 
   sh "pwd"
   sh "ls"
   sh "terraform --version"
   
   def dockerRunOpts = "--network host -e GRADLE_USER_HOME=./.gradle"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   sh "docker pull 507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   
   
   def localStackRunOpts = "--network host"
   def LOCALSTACK_IMAGE = "localstack/localstack"
   sh "docker pull localstack/localstack"
  
  
   docker.image(LOCALSTACK_IMAGE).withRun(localStackRunOpts){
            sh "terraform --version"
         docker.image(BUILD_IMAGE).inside(dockerRunOpts){

            
            sh "which gradle"
            sh "gradle --version"

     
            sh "./gradlew clean build --debug"

        }
   }
   

}

