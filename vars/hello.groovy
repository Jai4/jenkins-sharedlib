def call(String name = 'human') {
   echo "Hello, ${name}."
   def dockerRunOpts = "--network host -v /var/run/docker.sock:/var/run/docker.sock "
   sh "docker pull docker"
   docker.image('docker').inside(dockerRunOpts){
    sh "docker image ls"
    sh "ls"
  }
}

