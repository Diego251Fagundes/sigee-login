package br.com.sigee.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicação de autenticação do SIGEE.
 */
@SpringBootApplication
public class SigeeLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigeeLoginApplication.class, args);
	}

}
