package br.com.sigee.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa um usuario persistido no MongoDB.
 * A senha deve ser armazenada exclusivamente como hash.
 */
@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;

	private String nome;

	@Indexed(unique = true)
	private String email;

	private String senhaHash;

	private Role role;

	public Usuario() {
	}

	public Usuario(String nome, String email, String senhaHash, Role role) {
		this.nome = nome;
		this.email = email;
		this.senhaHash = senhaHash;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
