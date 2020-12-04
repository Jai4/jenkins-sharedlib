def call(String name = 'human') {
   echo "Hello, ${name}."
   def dockerRunOpts = "--network host"
   def BUILD_IMAGE = "507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   sh "docker pull 507997576901.dkr.ecr.us-east-2.amazonaws.com/zulugradle:0.1"
   docker.image(BUILD_IMAGE).inside(dockerRunOpts){
    sh "pwd"
  }
}

