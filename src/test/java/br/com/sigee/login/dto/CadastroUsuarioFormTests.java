package br.com.sigee.login.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class CadastroUsuarioFormTests {

	private Validator validator;

	@BeforeEach
	void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	void deveRejeitarDadosInvalidos() {
		CadastroUsuarioForm form = new CadastroUsuarioForm();
		form.setNome(" ");
		form.setNomeUsuario("usuario invalido");
		form.setEmail("email-invalido");
		form.setSenha("123");

		Set<String> camposInvalidos = validator.validate(form).stream()
				.map(ConstraintViolation::getPropertyPath)
				.map(Object::toString)
				.collect(Collectors.toSet());

		assertThat(camposInvalidos).contains("nome", "nomeUsuario", "email", "senha");
	}

	@Test
	void deveAceitarDadosValidos() {
		CadastroUsuarioForm form = new CadastroUsuarioForm();
		form.setNome("Maria Silva");
		form.setNomeUsuario("maria.silva");
		form.setEmail("maria@example.com");
		form.setSenha("senha-segura");

		assertThat(validator.validate(form)).isEmpty();
	}
}
