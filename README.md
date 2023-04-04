# Jenkins Shared Libraries

Jenkins Shared Libraries é uma funcionalidade do Jenkins que permite compartilhar código e funções entre pipelines e jobs. Isso permite que os desenvolvedores possam criar uma biblioteca comum de funções e utilizá-las em diferentes jobs ou pipelines, sem a necessidade de duplicar código em vários lugares.

A seguir, apresento uma documentação completa sobre o Jenkins Shared Libraries, incluindo os seguintes tópicos:

1. Configuração do Jenkins para o uso de Shared Libraries
2. Estrutura de diretórios de uma biblioteca compartilhada
3. Criação e utilização de funções em um pipeline
4. Exemplo de biblioteca compartilhada

## ****1. Configuração do Jenkins para o uso de Shared Libraries****

Antes de começar a utilizar as bibliotecas compartilhadas, é necessário configurar o Jenkins para utilizá-las. Para isso, siga os seguintes passos:

1. Na página inicial do Jenkins, clique em "Manage Jenkins" no menu lateral esquerdo.
2. Selecione "Configure System".
3. Role a página até a seção "Global Pipeline Libraries" e clique em "Add".
4. Na janela de configuração, preencha os seguintes campos:
   - Name: nome da biblioteca compartilhada
   - Default version: versão padrão da biblioteca
   - Retrieval method: método de obtenção da biblioteca. Pode ser "Modern SCM" (para usar um sistema de controle de versão moderno, como o Git) ou "Legacy SCM" (para usar um sistema de controle de versão mais antigo, como o Subversion)
   - Select source: informações para acesso ao repositório da biblioteca compartilhada (por exemplo, URL, credenciais, branch, etc.)
5. Clique em "Save" para salvar as alterações.

## 2. ****Estrutura de diretórios de uma biblioteca compartilhada****

Uma biblioteca compartilhada é um repositório de código que segue uma estrutura de diretórios específica. Essa estrutura é a seguinte:

```bash
src/
  org/
    jenkins/
      <library-name>/
        <arquivos.groovy>
vars/
  <nome-da-função>.groovy
resources/
  <arquivos-de-recurso>
```

- O diretório **`src`** contém arquivos de classe que podem ser importados e utilizados pelos pipelines.
- O diretório **`vars`** contém arquivos Groovy que definem funções que podem ser chamadas em um pipeline.
- O diretório **`resources`** contém arquivos de recurso que podem ser utilizados pelos pipelines, como por exemplo arquivos de configuração.

## ****3. Criação e utilização de funções em um pipeline****

Uma vez que a biblioteca compartilhada esteja configurada e a estrutura de diretórios esteja definida, é possível criar e utilizar funções em um pipeline. Para isso, siga os seguintes passos:

1. No pipeline, adicione a declaração **`@Library('<library-name>')`** no topo do script, onde **`<library-name>`** é o nome da biblioteca compartilhada configurada no Jenkins.
2. Utilize as funções definidas na biblioteca compartilhada como qualquer outra função do pipeline. Por exemplo:

```groovy
@Library('my-shared-library')  // Importa a biblioteca compartilhada
def myFunction() {
    // Define a função
```

3. Na etapa do pipeline em que você deseja usar a função, basta chamar a função pelo nome, como no exemplo a seguir:

```groovy
stage('Meu stage') {
  steps {
    script {
      def resultado = myFunction()
      // Use o resultado da função como necessário
    }
  }
}
```

## ****4. Exemplo de biblioteca compartilhada****

A seguir, apresento um exemplo simples de como criar uma biblioteca compartilhada que contém uma função que retorna a data e hora atual em um formato específico.

1. Crie um repositório Git para a biblioteca compartilhada.
2. Crie a estrutura de diretórios da biblioteca compartilhada no repositório Git:

```bash
src/
vars/
  formatDateTime.groovy
```

1. 1. Adicione o seguinte código ao arquivo **`formatDateTime.groovy`**:

```groovy
def call(format) {
    return new Date().format(format)
}
```

1. Faça o commit das alterações e faça o push para o repositório Git.
2. Configure o Jenkins para utilizar a biblioteca compartilhada conforme descrito no item 1.
3. Crie um novo pipeline e adicione o seguinte código:

```groovy
@Library('my-shared-library')  // Importa a biblioteca compartilhada

pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                script {
                    def now = formatDateTime('yyyy-MM-dd HH:mm:ss')
                    println "Current date and time: ${now}"
                }
            }
        }
    }
}
```

1. Execute o pipeline e verifique a saída para ver a data e hora formatada.

Neste exemplo, a função **`formatDateTime`** é definida no arquivo **`formatDateTime.groovy`** e pode ser importada pelo pipeline utilizando a declaração **`@Library('my-shared-library')`**. O pipeline utiliza a função para obter a data e hora atual em um formato específico e imprimir na saída do console.

É importante lembrar que este é apenas um exemplo simples e que as bibliotecas compartilhadas podem conter funções mais complexas e úteis para automatizar tarefas em pipelines e jobs do Jenkins.