package br.com.ricasfinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ricardo.mello
 *
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class RicasFinancasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicasFinancasApplication.class, args);
	}
}
