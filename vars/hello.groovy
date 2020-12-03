def call(String name = 'human') {
   echo "Hello, ${name}."
   def dockerRunOpts = "--network host -v /var/run/docker.sock:/var/run/docker.sock "
   sh "docker pull ubuntu"
   docker.image('ubuntu').inside(dockerRunOpts){
    
    sh "ls"
  }
}

