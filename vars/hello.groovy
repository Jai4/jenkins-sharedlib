def call(String name = 'human') {
   echo "Hello, ${name}."
   def dockerRunOpts = "--network host"
   sh "docker pull ubuntu"
   docker.image('ubuntu').inside(dockerRunOpts){
    sh "apt-get update"
    sh "apt-get install docker-ce docker-ce-cli containerd.io"
    sh "ls"
  }
}

