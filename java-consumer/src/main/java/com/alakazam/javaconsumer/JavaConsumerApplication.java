package com.alakazam.javaconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alakazam.javaconsumer.configuration.RabbitAmqpReceiversRunner;

/**
 * Classe principal do aplicativo Spring Boot, configurada para atuar como um consumidor de mensagens RabbitMQ.
 * Habilita agendamento de tarefas e define beans com base em perfis do Spring para controlar o comportamento da aplicação.
 */

@SpringBootApplication
@EnableScheduling
public class JavaConsumerApplication {

	/**
     * Fornece uma mensagem de uso quando o perfil 'usage_message' está ativo.
     * Este bean é destinado a auxiliar usuários a entenderem como executar a aplicação com os perfis apropriados.
     *
     * @return CommandLineRunner que imprime instruções de uso no console.
     */

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behavior.\n");
			System.out.println(
					"Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=transaction,receiver");
		};
	}

	/**
     * Cria um {@link CommandLineRunner} que inicia o receptor RabbitMQ, exceto quando o perfil 'usage_message' está ativo.
     * Este bean configura a aplicação para iniciar a escuta de mensagens RabbitMQ baseado no perfil ativo.
     *
     * @return CommandLineRunner que inicializa o receptor RabbitMQ.
     */

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner rabbitReceiver() {
		return new RabbitAmqpReceiversRunner();
	}

	/**
     * Método principal que serve como ponto de entrada para a aplicação Spring Boot.
     * Inicia a aplicação e processa argumentos de linha de comando.
     */
	
	public static void main(String[] args) {
		SpringApplication.run(JavaConsumerApplication.class, args);
	}

}
