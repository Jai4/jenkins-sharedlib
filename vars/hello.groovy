def call(String name = 'human') {
   echo "Hello, ${name}."
   echo "Present working directory of my jenkins build is: " 
   sh "pwd"
   sh "ls"
   def dockerRunOpts = "--network host -e GRADLE_USER_HOME=./.gradle -v /var/run/docker.sock:/var/run/docker.sock"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   sh "docker pull 507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
  
   
   docker.image(BUILD_IMAGE).withRun(dockerRunOpts){
   
      sh "pwd"
      sh "ls"
      sh "which gradle"
      sh "gradle --version"
      
      
      sh "docker --version"
      
      sh "docker pull localstack/localstack"
      sh "docker run localstack/localstack"
      
      sh "./gradlew clean build"
       
  
      
  }
   
   echo "see whether u can see a build directory"
   sh "ls"
}

