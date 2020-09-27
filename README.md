# INTEGRATIONS E DEVELOPMENT TOOLS #
## Integrantes: ##
Carlos Mendes  
Felipe Birches  
Guilherme Moraes  
Leonardo Villani  
Luiz Galesso  

## Tecnologias utilizadas ##
### Front: React JS ###
### Back: Java utilizando Spring Boot ###
### RabbitMQ utilizando a plataforma CloudAMQP ###

## Topologia utilizada ##  
Foi utlizada a topologia direta, para a qual foi criado um exchange, ao receber as informações do drone, o serviço começa a enviar mensagens com um intervalo de 10 segundos, se a condição especificada for violada, é encerrado o envio de mensagens.  
O consumidor se conecta nesse único exchange e começa a consumir as informações, e testa as condições especificadas, que se violadas, são impressas no console as informações do drone.  

## Como executar ##
Uma das maneiras de se executar o projeto, é ter instalado o docker compose, caso tenha instalado, execute o seguinte comando:
```
docker-compose up --build 
```
Após o comando ser executado, acesse o endereço http://127.0.0.1:80/ a aplicação do front estará disponível exibindo o form para cadastro de drones e uma tabela que exibe os drones cadastrados e permite visualizar a localização deles.

Outra maneira de se executar o projeto, caso não tenha o docker-compose instalado é:  
Acesse o diretório do projeto back com o comando:
```
cd back
```  
e Execute em seguida:
```
./mvnw spring-boot:run
```
Em seguida retroceda ao diretório raiz e acesse o diretório do projeto front comos comandos:
```
cd ..
cd drone-front
``` 
É necessário ter o node versão 12 instalado pelo menos e o npm versão 6.14 pelo menos, e execute os seguintes comandos:  
```
npm install
npm start 
```
Após os comandos serem executados com sucesso, acesse a aplicação no endereço http://127.0.0.1:3000/  
