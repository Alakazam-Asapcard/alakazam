package com.alakazam.javaconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * Classe responsável por receber e processar mensagens de uma fila RabbitMQ.
 * Utiliza Spring AMQP para configurar um listener que automaticamente consome mensagens de uma fila específica.
 */

public class TransactionReceiver {

	/**
     * Método para receber e processar mensagens da fila RabbitMQ.
     * Este método é invocado automaticamente quando uma mensagem é publicada na fila configurada.
     * 
     * Utiliza um {@link StopWatch} para medir o tempo de processamento da mensagem, demonstrando
     * uma forma simples de monitoramento de desempenho.
     * 
     * @param in A mensagem recebida da fila RabbitMQ. 
	 * O conteúdo da mensagem é interpretado como uma string.
     * @throws InterruptedException Lançada se o thread é interrompido enquanto espera.
     */


	@RabbitListener(queues = "#{autoDeleteQueue.name}")
	public void receive(String in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println(" [x] Received '" + in + "'");
		watch.stop();
		System.out.println(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

}
