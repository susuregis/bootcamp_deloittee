package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario criarUsuario(Usuario usuario) {
		validarDadosUsuario(usuario);

		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new IllegalArgumentException(
				String.format("Já existe um usuário cadastrado com o email: %s", usuario.getEmail())
			);
		}

		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		System.out.println("Usuário criado com sucesso: " + usuarioSalvo.getNome());
		return usuarioSalvo;
	}

	@Transactional(readOnly = true)
	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		System.out.println("Total de usuários encontrados: " + usuarios.size());
		return usuarios;
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		validarId(id);

		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new UsuarioNotFoundException(id));

		System.out.println("Usuário encontrado: " + usuario.getNome());
		return usuario;
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("O email não pode ser vazio.");
		}

		return usuarioRepository.findByEmail(email)
			.orElseThrow(() -> new UsuarioNotFoundException(
				String.format("Usuário com email '%s' não foi encontrado.", email)
			));
	}

	public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
		validarId(id);
		validarDadosUsuario(usuarioAtualizado);

		Usuario usuarioExistente = usuarioRepository.findById(id)
			.orElseThrow(() -> new UsuarioNotFoundException(id));

		if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail()) &&
			usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())) {
			throw new IllegalArgumentException(
				String.format("O email '%s' já está sendo usado por outro usuário.",
					usuarioAtualizado.getEmail())
			);
		}


		usuarioExistente.setNome(usuarioAtualizado.getNome());
		usuarioExistente.setEmail(usuarioAtualizado.getEmail());




		Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
		System.out.println("Usuário atualizado com sucesso: " + usuarioSalvo.getNome());
		return usuarioSalvo;
	}

	public void removerUsuario(Long id) {
		validarId(id);

		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new UsuarioNotFoundException(id));

		usuarioRepository.deleteById(id);
		System.out.println("Usuário removido com sucesso: " + usuario.getNome());
	}

	@Transactional(readOnly = true)
	public boolean existeUsuario(Long id) {
		validarId(id);
		return usuarioRepository.existsById(id);
	}

	private void validarId(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID do usuário deve ser um número válido maior que zero.");
		}
	}

	private void validarDadosUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Os dados do usuário não podem ser nulos.");
		}

		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do usuário é obrigatório.");
		}

		if (usuario.getNome().trim().length() < 3) {
			throw new IllegalArgumentException("O nome do usuário deve ter no mínimo 3 caracteres.");
		}

		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("O email do usuário é obrigatório.");
		}

		if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			throw new IllegalArgumentException("O email fornecido não é válido.");
		}
	}
}
