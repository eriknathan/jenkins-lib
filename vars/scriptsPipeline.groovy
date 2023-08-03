// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptbash = libraryResource 'com/scripts/'

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('ExecShellScritp') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						writeFile file: './segredos.sh', text: scriptbash

						sh 'bash ./segredos.sh'
					}
				}
			}
		}
	}
}