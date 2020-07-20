package dev.mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MissionAppApplication {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext context = SpringApplication.run(MissionAppApplication.class, args)) {
	
		}
	}
}
