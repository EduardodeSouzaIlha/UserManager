
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

Agora basta acessar o Spring Boot Dashboard e rodar o projeto (para rodar o backend é necessário abrir a pasta do backend no vscode pois se tiver outros projetos além do spring, pode ser que o spring deshboard não encontre o projeto), apenas estarão disponiveis rotas e um banco H2 com alguns dados inseridos.

- Banco H2 : [http://localhost:8080/h2](http://localhost:8080/h2)
- Spring server : [http://localhost:8080/](http://localhost:8080)

Será possivel acessar o banco de dados usando os seguintes dados:

- user: sa
- password: password

lembrando que a configuração do banco se encontra no arquivo aplication.properties

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


## Sobre a aplicação

Foram aplicadas validações em todos os campos, também não é permitido cadastrar emails igual ou alterar os campos via html, pois os dados serão interceptados pelo backend recebendo um 404. O projeto conta com duas tabelas no banco de dados user_entity e gender.

## Link do video de apresentação 
[Video de apresentação][https://youtu.be/yYM-hFNDEKA]



