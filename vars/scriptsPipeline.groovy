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
						
						//def scriptbash = libraryResource 'com/scripts/segredos.sh'
						//writeFile file: './segredos.sh', text: scriptbash
						//sh 'bash ./segredos.sh Erik Quinta'

						sh scriptLib.testScript(Script: "segredos",
												Nome: "Erik",
												Day: "Quinta")
					}
				}
			}
		}
	}
}