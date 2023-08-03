// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptcontents = libraryResource "resource/scripts/${config.name}"
	writeFile file: "${config.name}", text: scriptcontents
	sh "chmod a+x ./${config.name}"
	
	loadLinuxScript(name: 'hello-world.sh')
	sh "./hello-world.sh ${config.name} ${config.dayOfWeek}"

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('Teste SH') {
                steps {
                    script {
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "

						helloWorldExternal(name:"Erik", dayofWeek:"Quinta-Feira")
					}
				}
			}
		}
	}
}