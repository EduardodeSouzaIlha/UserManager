
# Gestão de Usuario (Crud)


## Instalação

Clone o projeto

```bash
  git clone https://github.com/EduardodeSouzaIlha/UserManagement.git
```
    
## Run Backend

Acesse o diretorio

```bash
  cd UserManagement/Backend
```

É necessário instalar o Maven (Versão 3.6.3 ) e o Java 17 (OpenJDK), sendo que o maven a instação deve ser realizada a partir do link [Maven 3.6.3](https://maven.apache.org/docs/3.6.3/release-notes.html)

Instalação do JDK e JRE

```bash
  sudo apt install openjdk-17-jdk openjdk-17-jre
```

IDE - Para o desenvolvimento utilizei o Vscode (Você pode usar outra) assim instalei as extensões  

- Spring Boot Dashboard (Verifica os projetos spring e facilita a inicialização)
- Spring Boot Tools (Se ativa ao editar aquivos .java)
- Spring Initilizr Java Support (Criador de projetos Spring CTRL + P)

Agora basta acessar o Spring Boot Dashboard e rodar o projeto, apenas estarão disponiveis rotas e um banco H2 com alguns dados inseridos.

- Banco H2 : [http://localhost:8081/](http://localhost:8080/h2)
- Spring server : [http://localhost:8080/](http://localhost:8080)

Será possivel acessar o banco de dados usando a senha:

- password

### Scripts SQL

No momento em que o servidor do Spring Boot inicie também o banco de dados será populado com algumas informações sendo possivel fazer consultas tais como: 

```bash
  SELECT user_entity.name, gender.gender FROM user_entity INNER JOIN gender ON gender.id = user_entity.gender_id;
```



## Run Frontend

Primeiro você deve ter o yarn instalado

Acesse o diretorio

```bash
  cd UserManagement/Frontend
```
Crie o arquivo .env na pasta Frontend e dentro coloque a seguinte variavel de ambiente


`VUE_APP_API_URL=http://localhost:8080`


Instale as dependencias 
```bash
  yarn install
```
Crie o servidor
```bash
  yarn serve
```
Logo o projeto vai estar disponivel em
[http://localhost:8081/](http://localhost:8081/)



## Difereças

JDK (Java Development Kit):

O JDK é um conjunto de ferramentas de desenvolvimento completo para criar aplicativos Java.
Inclui o JRE, o compilador Java (javac), as bibliotecas Java (APIs) e várias ferramentas de desenvolvimento e depuração.
É usado por desenvolvedores para escrever, compilar, depurar e executar aplicativos Java.

JRE (Java Runtime Environment):

O JRE é uma parte do JDK que fornece o ambiente de tempo de execução para executar aplicativos Java.
Inclui a máquina virtual Java (JVM) e as bibliotecas Java padrão (APIs) necessárias para executar aplicativos Java.
É usado pelos usuários finais para executar aplicativos Java sem a necessidade de instalar o JDK completo.


