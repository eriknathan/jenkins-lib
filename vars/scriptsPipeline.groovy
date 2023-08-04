// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def cleanLib = new com.functions.CleanLib()
	def projectBaseName = "cybersec-storybook"

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('Script Shell') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						def scriptbash = libraryResource 'com/scripts/segredos.sh'
						writeFile file: './segredos.sh', text: scriptbash
						sh 'bash ./segredos.sh Erik 21 Masc'

						sh cleanLib.cleanFiles(File: "segredos.sh")
					}
				}
			}

			stage('Script Python') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT PYTHON "
						echo " --------------------------------------------------------------------------------------- "

						def scriptpython = libraryResource 'com/scripts/teste.py'
						writeFile file: './teste.py', text: scriptpython
						sh 'python3 ./teste.py Erik'
						
						sh cleanLib.cleanFiles(File: "teste.py")
					}
				}
			}

			stage('Script Json') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT JSON "
						echo " --------------------------------------------------------------------------------------- "

						//def scriptjson = libraryResource 'com/scripts/request.json'
						//def configFileMap = readJSON file: scriptjson

						def jsonData = readJSON(file: 'com/scripts/request.json')
						echo "${jsonData}"
						echo "Valor da chave 'campo' no JSON: ${jsonData.cybersec-storybook}"
						// Aqui você pode adicionar mais lógica para processar os dados do JSON

						// Selecione o pipeline específico
						//def pipelineName = 'cybersecPipeline'
						//def pipelineConfig = configFileMap[pipelineName]

						//echo "Expondo Json: ${pipelineConfig}"

						//if(pipelineConfig != null) {
						//	copyFiles(ProjectName: projectBaseName, pipelineConfig)
						//}						
					}
				}
			}
		}
	}
}

// COPIANDO ARQUIVOS
def copyFiles(Map params, Map pipelineConfig){
    if (pipelineConfig.containsKey(params.ProjectName) && pipelineConfig[params.ProjectName].containsKey(env.BRANCH_NAME)) {
        pipelineConfig[params.ProjectName][env.BRANCH_NAME].each { file ->
            configFileProvider([configFile(fileId: file.fileId, targetLocation: file.targetLocation)]) {}
        }
    }
}