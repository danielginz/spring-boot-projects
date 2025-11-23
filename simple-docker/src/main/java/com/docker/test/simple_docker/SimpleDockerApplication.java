package com.docker.test.simple_docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SimpleDockerApplication {
	public static final List<String> WORDS = List.of(
			"AAA",
			"BBB",
			"CCC",
			"DDD",
			"EEE"
	);

	private static final Random RANDOM = new Random();

	public static void main(String[] args) {
		SpringApplication.run(SimpleDockerApplication.class, args);

		while (true) {
			String randomPhrase = WORDS.get(RANDOM.nextInt(WORDS.size()));
			System.out.println(randomPhrase);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}

