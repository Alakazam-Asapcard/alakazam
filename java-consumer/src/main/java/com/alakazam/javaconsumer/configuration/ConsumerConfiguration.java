package com.alakazam.javaconsumer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alakazam.javaconsumer.consumer.TransactionReceiver;

@Profile({ "transaction" })
@Configuration
public class ConsumerConfiguration {

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("transactions", false, false);
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue autoDeleteQueue() {
			return new AnonymousQueue();
		}

		@Bean
		public Binding binding(FanoutExchange fanout, Queue autoDeleteQueue) {
			return BindingBuilder.bind(autoDeleteQueue).to(fanout);
		}

		@Bean
		public TransactionReceiver receiver() {
			return new TransactionReceiver();
		}
	}
}
