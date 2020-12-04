def call(String name = 'human') {
   echo "Hello, ${name}."
   echo "Present working directory of my jenkins build is: " 
   sh "pwd"
   def dockerRunOpts = "--network host -e GRADLE_USER_HOME=./.gradle"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   sh "docker pull 507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   docker.image(BUILD_IMAGE).inside(dockerRunOpts){
   
      sh "pwd"
      sh "ls"
      sh "cd ./"
      sh "ls"
      sh "which gradle"
      sh "gradle --version"
  }
}

