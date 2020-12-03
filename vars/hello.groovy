def call(String name = 'human') {
   echo "Hello, ${name}."
   def dockerRunOpts = "--network host"
   sh "docker pull docker"
   docker.image('docker').inside(dockerRunOpts){
    sh "ls"
  }
}

