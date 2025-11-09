package com.camilly.api.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilly.api.rest.dto.UsuarioMapper;
import com.camilly.api.rest.dto.UsuarioRequest;
import com.camilly.api.rest.dto.UsuarioResponse;
import com.camilly.api.rest.model.Usuario;
import com.camilly.api.rest.repository.UsuarioRepository;

// Camada de serviço para lógica de negócios relacionada a usuários
@Service
public class UsuarioService {
    // Injeção do repositório de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Métodos para operações CRUD
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioRequest);
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> buscarTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse buscarUsuarioPorId(@lombok.NonNull Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioMapper::toResponse).orElse(null);
    }

    public UsuarioResponse atualizarUsuario(@lombok.NonNull Long id, UsuarioRequest usuarioRequest) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioRequest.getNome());
                    usuario.setSobrenome(usuarioRequest.getSobrenome());
                    usuario.setIdade(usuarioRequest.getIdade());
                    usuario.setOcupacao(usuarioRequest.getOcupacao());
                    usuarioRepository.save(usuario);
                    return UsuarioMapper.toResponse(usuario);
                })
                .orElse(null);
    }

    public boolean deletarUsuario(@lombok.NonNull Long id) {
        if(!usuarioRepository.existsById(id)){
            return false;
        }

        usuarioRepository.deleteById(id);
        return true;
    }
}
