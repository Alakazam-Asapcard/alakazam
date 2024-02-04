# Projeto de Integração RabbitMQ com Spring Boot e JavaScript

Este projeto demonstra uma integração eficaz entre uma aplicação produtora de mensagens em JavaScript, utilizando RabbitMQ como intermediário de mensagens, e uma aplicação consumidora em Java Spring Boot, que consome e processa estas mensagens.

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

3. **Inicie o produtor de mensagens Node.js**

Dentro do diretório do produtor execute:

```bash
npm install
node producer.js
```

Este script vai iniciar a publicação de mensagens em um canal do RabbitMQ.

4. **Inicie a aplicação consumidora no Spring Boot**

Dentro do diretório do consumidor execute:

```bash
mvn clean install
mvn spring-boot:run
```

Esta aplicação vai consumir as mensagens publicadas pelo produtor Node.js.

## Como usar

Após iniciar tanto o produtor quanto o consumidor, o sistema estará em plena operação. O produtor Node.js publicará mensagens em um canal do RabbitMQ, que serão consumidas pela aplicação Spring Boot. A lógica de processamento das mensagens pode ser ajustada conforme a necessidade do seu projeto.


