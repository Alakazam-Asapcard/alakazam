#!/usr/bin/env node

var amqp = require('amqplib/callback_api');
const csv = require('csvtojson');

/**
 * Estabelece uma conexão com o servidor RabbitMQ e publica mensagens contidas em um arquivo CSV para uma exchange do tipo fanout.
 * 
 * @param {Object} error0 - Representa um erro que pode ocorrer durante a tentativa de conexão com o servidor RabbitMQ. Caso um erro ocorra, o processo é interrompido e o erro é lançado.
 * @param {Object} connection - Objeto de conexão com o servidor RabbitMQ, utilizado para criar canais de comunicação.
 */
amqp.connect('amqp://localhost', function(error0, connection){
    // Verifica a existência de um erro na conexão e lança uma exceção caso positivo.
    if(error0) {
        throw error0;
    }

    /**
    * Cria um canal de comunicação com o RabbitMQ e publica mensagens para uma exchange do tipo fanout.
     * 
     * @param {Object} error1 - Representa um erro que pode ocorrer durante a criação do canal. Caso um erro ocorra, o processo é interrompido e o erro é lançado.
     * @param {Object} channel - Canal de comunicação com o RabbitMQ, utilizado para definir exchanges e publicar mensagens.
     */
    connection.createChannel(function(error1, channel) {
        // Verifica a existência de um erro na criação do canal e lança uma exceção caso positivo.
        if (error1) {
            throw error1;
        }

        var exchange = 'transactions';
        const csvFilePath = '../INPUT/input-data.csv';

        // Declara uma exchange do tipo 'fanout' que será utilizada para publicar as mensagens.
        channel.assertExchange(exchange, 'fanout', {
            durable: false
        });

        // Converte o arquivo CSV em objetos JSON, definindo os cabeçalhos das colunas.
        csv({
            delimiter: ';',
            noheader: true,
            headers: ['Transaction_ID', 'Transaction_Date', 'Person_ID', 'Name', 'Age', 'Amount', 'Installment_Number']
        }).fromFile(csvFilePath).then((jsonObj) => {
            for(let line of jsonObj) {
                channel.publish(exchange, '', Buffer.from(JSON.stringify(line)));
            }

            // Aguarda 500 milissegundos antes de fechar a conexão e encerrar o processo, garantindo que todas as mensagens sejam publicadas.
            setTimeout(function() {
                connection.close();
                process.exit(0);
            }, 500);
        
        });
    
    });
});