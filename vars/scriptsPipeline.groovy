// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.scripts.ScriptLib()
	def cleanLib = new com.functions.CleanLib()

    pipelineParams.scriptShell = "segredos.sh"
    pipelineParams.scriptPython = "teste.py"
    

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
						
						def scriptbash = libraryResource "com/scripts/${pipelineParams.scriptShell}"
						writeFile file: "./${pipelineParams.scriptShell}", text: scriptbash
						sh "bash ./${pipelineParams.scriptShell}.sh Erik 21 Masc"

						sh cleanLib.cleanFiles(File: pipelineParams.scriptShell)

						def scriptpython = libraryResource "com/scripts/${pipelineParams.scriptShell}"
						writeFile file: "./${pipelineParams.scriptPython}", text: scriptpython
						sh "python3 ./${pipelineParams.scriptShell} Erik"
						
						sh cleanLib.cleanFiles(File: pipelineParams.scriptPython)
					}
				}
			}
		}
	}
}