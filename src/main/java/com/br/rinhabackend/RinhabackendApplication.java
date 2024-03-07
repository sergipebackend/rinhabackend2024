package com.br.rinhabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RinhabackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RinhabackendApplication.class, args);
	}
}
