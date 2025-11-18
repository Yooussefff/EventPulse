package com.EventPulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class EventPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventPulseApplication.class, args);
	}

}
