job('NodeJS Docker example') {
    scm {
        git('https://github.com/eshaparenko/node-app-docker.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Test')
            node / gitConfigEmail('jenkins-dsl@test.com')
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
        dockerBuildAndPublish {
            repositoryName('eshaparenko/docker-nodejs-repo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
