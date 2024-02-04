package com.alakazam.javaconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alakazam.javaconsumer.configuration.RabbitAmqpReceiversRunner;

@SpringBootApplication
@EnableScheduling
public class JavaConsumerApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behavior.\n");
			System.out.println(
					"Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=transaction,receiver");
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner rabbitReceiver() {
		return new RabbitAmqpReceiversRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaConsumerApplication.class, args);
	}

}
