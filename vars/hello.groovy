def call(String name = 'human') {
   echo "Hello, ${name}."
   def dockerRunOpts = "--network host"
   sh "docker pull hello-world"
   docker.image('hello-world').inside(dockerRunOpts){
    sh "ls"
  }
}

