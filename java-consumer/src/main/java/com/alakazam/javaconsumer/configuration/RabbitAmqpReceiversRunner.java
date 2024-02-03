package com.alakazam.javaconsumer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

public class RabbitAmqpReceiversRunner implements CommandLineRunner {

	@Value("${rabbitReceiver.client.duration:0}")
	private int duration;

	@Autowired
	private ConfigurableApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ready ... running for " + duration + "ms");
		Thread.sleep(duration);
		ctx.close();

	}

}
