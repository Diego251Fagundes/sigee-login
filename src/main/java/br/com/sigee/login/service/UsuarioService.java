package br.com.sigee.login.service;

import java.util.Locale;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sigee.login.dto.CadastroUsuarioForm;
import br.com.sigee.login.exception.EmailJaCadastradoException;
import br.com.sigee.login.model.Role;
import br.com.sigee.login.model.Usuario;
import br.com.sigee.login.repository.UsuarioRepository;

/**
 * Aplica as regras do cadastro antes de persistir um usuario.
 */
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Usuario cadastrar(CadastroUsuarioForm form) {
		String emailNormalizado = normalizarEmail(form.getEmail());

		if (usuarioRepository.existsByEmail(emailNormalizado)) {
			throw new EmailJaCadastradoException();
		}

		Usuario usuario = new Usuario(
				form.getNome().trim(),
				emailNormalizado,
				passwordEncoder.encode(form.getSenha()),
				Role.PROFESSOR
		);

		try {
			return usuarioRepository.save(usuario);
		} catch (DuplicateKeyException exception) {
			throw new EmailJaCadastradoException(exception);
		}
	}

	private String normalizarEmail(String email) {
		return email.trim().toLowerCase(Locale.ROOT);
	}
}
