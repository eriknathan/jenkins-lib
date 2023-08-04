// vars/mouraconnectors.groovy

def call (Map pipelineParams) {
    
    def dockerLib = new com.docker.DockerLib()
	def projectName = pipelineParams.projectName
	def branchName = env.BRANCH_NAME

	//Parâmetros do Docker
	def dockerImage = "${ECR_DEFAULT_REGISTRY}/${pipelineParams.projectName}:${BRANCH_NAME}-${BUILD_NUMBER}"
	def dockerComposeFile = null
	def dockerfilePath = "Dockerfile"
	def dockerContext = "."
	
	//IDs dos arquivos .env no Jenkins Managed Files
	def configFileMap = [
		'mouraconnectors': [
			'develop': '83b08c3d-de96-41ee-a791-4c2a2c13eb69',
			'qa': '8d158882-9845-4ffb-812d-42430ffd083a',
			'homolog': '58756e95'
		]
	]

	// Pipeline Declarativa
	pipeline {
		agent { 
            label 'infra_isi_app'
        }

		stages {

			stage  ('Image Build') {
				steps {
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O BUILD DA IMAGEM: ${dockerImage}"
						echo " --------------------------------------------------------------------------------------- "
						
						copyFiles(ProjectName: projectName, configFileMap)

						sh dockerLib.imgBuildPhase(DockerfilePath: dockerfilePath,
												    DockerImage: dockerImage,
													DockerContext: dockerContext)
					}
				}
			}
		}
    }
}

// COPIANDO ARQUIVOS DAS .ENVs
def copyFiles(Map params, Map configFileMap){
	if (configFileMap.containsKey(params.ProjectName) && configFileMap[params.ProjectName].containsKey(env.BRANCH_NAME)) {
		def fileId = configFileMap[params.ProjectName][env.BRANCH_NAME]
		configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
	}
}