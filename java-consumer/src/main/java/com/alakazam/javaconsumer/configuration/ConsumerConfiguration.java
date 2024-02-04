package com.alakazam.javaconsumer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alakazam.javaconsumer.consumer.TransactionReceiver;

@Profile({ "transaction" })
@Configuration
public class ConsumerConfiguration {

	/**
	* Cria e configura uma FanoutExchange denominada 'transactions'. 
     * Uma FanoutExchange roteia mensagens para todas as filas vinculadas, ignorando a chave de roteamento.
     * 
     * @return Uma instância de FanoutExchange configurada para não ser durável e não ser auto-deletável.
     */
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("transactions", false, false);
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		/**
         * Cria uma fila anônima que será excluída automaticamente quando o consumidor se desconectar.
         * 
         * @return Uma instância de AnonymousQueue, que é uma fila com um nome gerado aleatoriamente.
         */
		@Bean
		public Queue autoDeleteQueue() {
			return new AnonymousQueue();
		}

		/**
         * Cria uma vinculação entre a FanoutExchange 'transactions' e a fila anônima.
         * Esta configuração garante que todas as mensagens enviadas à exchange serão roteadas para esta fila.
         * 
         * @param fanout A FanoutExchange à qual a fila será vinculada.
         * @param autoDeleteQueue A fila que receberá as mensagens da exchange.
         * 
         * @return Uma instância de Binding que representa a relação entre a exchange e a fila.
         */
		@Bean
		public Binding binding(FanoutExchange fanout, Queue autoDeleteQueue) {
			return BindingBuilder.bind(autoDeleteQueue).to(fanout);
		}

		/**
         * Cria uma instância do receptor de transações que será responsável por processar as mensagens recebidas.
         * 
         * @return Uma instância de TransactionReceiver, o componente que consumirá e processará as mensagens da fila.
         */
		@Bean
		public TransactionReceiver receiver() {
			return new TransactionReceiver();
		}
	}
}
