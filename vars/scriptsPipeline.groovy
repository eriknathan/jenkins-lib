// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('ExecShellScritp') {
                steps {
					echo " --------------------------------------------------------------------------------------- "
					echo " INICIANDO O TESTE DO SCRIPT SH "
					echo " --------------------------------------------------------------------------------------- "
					
					def scriptbash = libraryResource 'com/scripts/segredos.sh'

					writeFile file: './segredos.sh', text: scriptbash

					sh 'bash ./segredos.sh'
				}
			}
		}
	}
}