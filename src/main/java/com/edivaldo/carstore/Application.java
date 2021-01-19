package com.edivaldo.carstore;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 * Classe de Application
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@EnableJpaAuditing
@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private RunApp app;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("---------------------------------------------------->>>>>>>>>>");
		System.out.println("-----------------------START APP--------------------->>>>>>>>>");
		System.out.println("---------------------------------------------------->>>>>>>>>>");

		app.appStart();

	}
}