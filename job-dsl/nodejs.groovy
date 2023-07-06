job('NodeJS example') {
    scm {
        git('https://github.com/eshaparenko/node-app-docker') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Eugene')
            node / gitConfigEmail('gesha8610@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("npm install")
    }
}
