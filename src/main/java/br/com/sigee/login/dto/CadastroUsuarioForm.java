package br.com.sigee.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Dados recebidos pelo formulario de cadastro de usuario.
 */
public class CadastroUsuarioForm {

	@NotBlank(message = "Informe o nome.")
	@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
	private String nome;

	@NotBlank(message = "Informe o nome de usuario.")
	@Size(min = 3, max = 30, message = "O nome de usuario deve ter entre 3 e 30 caracteres.")
	@Pattern(
			regexp = "^[A-Za-z0-9._-]+$",
			message = "O nome de usuario deve conter apenas letras, numeros, ponto, hifen ou sublinhado."
	)
	private String nomeUsuario;

	@NotBlank(message = "Informe o e-mail.")
	@Email(message = "Informe um e-mail valido.")
	@Size(max = 254, message = "O e-mail deve ter no maximo 254 caracteres.")
	private String email;

	@NotBlank(message = "Informe a senha.")
	@Size(min = 8, max = 72, message = "A senha deve ter entre 8 e 72 caracteres.")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
