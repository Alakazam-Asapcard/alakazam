# Projeto de Integração RabbitMQ com Spring Boot e JavaScript para Processamento de Transações Financeiras

Este projeto ilustra a implementação de um sistema robusto para o processamento de transações financeiras, com uma aplicação produtora responsável pela leitura e publicação de transações a partir de arquivos CSV em JavaScript, e uma aplicação consumidora em Java Spring Boot, que consome essas mensagens, realiza a validação das transações através de um segundo arquivo de conciliação e persiste os dados validados em um banco de dados relacional.

## Pré-requisitos

Para executar este projeto, você precisará ter instalado:

- [Node.js](https://nodejs.org/)
- [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ou superior
- [Maven](https://maven.apache.org/)
- [RabbitMQ](https://www.rabbitmq.com/download.html)

## Instalação

Siga os passos abaixo para configurar o ambiente de desenvolvimento:

1. **Clone o repositório**

```bash
git clone https://github.com/Alakazam-Asapcard/alakazam.git
cd alakazam
```

2. **Configure e inicie o RabbitMQ**

Garanta que o RabbitMQ esteja instalado e em execução na sua máquina. Por padrão, o RabbitMQ inicia um servidor na porta 5672.

3. **Inicie a aplicação consumidora no Spring Boot**

Dentro do diretório do consumidor execute:

```bash
cd java-consumer
./mvnw clean package
java -jar target/java-consumer-0.0.1-SNAPSHOT.jar --spring.profiles.active=transaction,receiver --rabbitReceiver.client.duration=60000
```

Esta aplicação vai consumir as mensagens publicadas pelo produtor Node.js.

4. **Inicie o produtor de mensagens Node.js**

Dentro do diretório do produtor execute:

```bash
cd ..
cd javascript-producer
npm install
node producer.js
```

Este script vai iniciar a publicação de mensagens em um canal do RabbitMQ.



## Como usar

Após iniciar tanto o consumidor quanto o produtor, o sistema estará em plena operação. O produtor Node.js publicará mensagens em um canal do RabbitMQ, que serão consumidas pela aplicação Spring Boot. A lógica de processamento das mensagens pode ser ajustada conforme a necessidade do seu projeto. Caso o usuário queira utilizar um arquivo diferente, ele deve adicioná-lo à pasta INPUT com o mesmo nome do arquivo CSV anterior e executar o producer.js novamente

## Autores

- [Carina Maleski](https://github.com/carina-maleski)

- [João Bezerra](https://github.com/Joaobezerrajr)

- [Daniel Lugli](https://github.com/luglifilho)

- [Raphael Luccas](https://github.com/RaphaelLuccas)

- [Giovanni Torelli](https://github.com/Torelli)

- [Luciana Ramos](https://github.com/xluhramosx)


