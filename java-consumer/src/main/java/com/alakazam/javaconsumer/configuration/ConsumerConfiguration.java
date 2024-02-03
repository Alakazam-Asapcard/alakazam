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
		return new FanoutExchange("transactions");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue filaTeste() {
			return new Queue("filateste");
		}

		/*@Bean
		public Queue autoDeleteQueue2() {
			return new AnonymousQueue();
		}*/

		@Bean
		public Binding binding1(FanoutExchange fanout, Queue filaTeste) {
			return BindingBuilder.bind(filaTeste).to(fanout);
		}

		/*@Bean
		public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
		}*/

		@Bean
		public TransactionReceiver receiver() {
			return new TransactionReceiver();
		}
	}
}
