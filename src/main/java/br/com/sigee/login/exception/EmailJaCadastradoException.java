package br.com.sigee.login.exception;

/**
 * Indica que o e-mail informado ja pertence a outro usuario.
 */
public class EmailJaCadastradoException extends RuntimeException {

	public EmailJaCadastradoException() {
		super("O e-mail informado ja esta cadastrado.");
	}

	public EmailJaCadastradoException(Throwable cause) {
		super("O e-mail informado ja esta cadastrado.", cause);
	}
}
