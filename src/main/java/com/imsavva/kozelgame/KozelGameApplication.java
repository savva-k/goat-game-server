package com.imsavva.kozelgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KozelGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(KozelGameApplication.class, args);
	}
}
