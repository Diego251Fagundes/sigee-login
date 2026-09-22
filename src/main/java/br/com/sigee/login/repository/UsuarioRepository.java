package br.com.sigee.login.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sigee.login.model.Usuario;

/**
 * Fornece as operacoes de persistencia e consulta de usuarios.
 */
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Optional<Usuario> findByNomeUsuario(String nomeUsuario);

	Optional<Usuario> findByEmail(String email);

	boolean existsByNomeUsuario(String nomeUsuario);

	boolean existsByEmail(String email);
}
