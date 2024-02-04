package com.alakazam.javaconsumer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Classe responsável por controlar a execução do consumidor RabbitMQ, definindo um tempo de execução
 * após o qual o aplicativo se encerra automaticamente.
 */

public class RabbitAmqpReceiversRunner implements CommandLineRunner {

	/**
     * Valor configurável que define a duração da execução do consumidor. 
     * O valor padrão é 0, o que significa que, por padrão, o consumidor não tem um tempo de execução limitado
     * a menos que seja especificado na configuração.
     */

	@Value("${rabbitReceiver.client.duration:0}")
	private int duration;

	/**
     * Contexto da aplicação Spring, injetado automaticamente pelo Spring Framework.
     * Utilizado para fechar o contexto (e, portanto, a aplicação) após o tempo de execução definido.
     */

	@Autowired
	private ConfigurableApplicationContext ctx;

	/**
     * Inicia a execução do consumidor RabbitMQ e aguarda um período especificado pela propriedade 'duration'
     * antes de fechar o contexto da aplicação, encerrando-a.
     *
     * @param args Argumentos da linha de comando passados para o aplicativo.
     * @throws Exception Exceção lançada em caso de interrupções ou erros durante a espera.
     */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ready ... running for " + duration + "ms");
		Thread.sleep(duration);
		ctx.close();

	}

}
