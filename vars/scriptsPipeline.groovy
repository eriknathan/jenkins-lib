// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.scripts.ScriptLib()

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('TesteShell') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						def scriptbash = libraryResource 'com/scripts/segredos.sh'
						writeFile file: './segredos.sh', text: scriptbash
						sh 'bash ./segredos.sh Erik 21 Masc'

						def scriptbash = libraryResource 'com/scripts/teste.py'
						writeFile file: './teste.py', text: scriptbash
						sh 'python3 ./teste.py Erik'
					}
				}
			}
		}
	}
}