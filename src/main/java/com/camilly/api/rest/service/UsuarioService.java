package com.camilly.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilly.api.rest.model.Usuario;
import com.camilly.api.rest.repository.UsuarioRepository;

// Camada de serviço para lógica de negócios relacionada a usuários
@Service
public class UsuarioService {
    // Injeção do repositório de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Métodos para operações CRUD
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioDetalhes){
        Usuario usuario = buscarUsuarioPorId(id);
        if(usuario != null){
            usuario.setNome(usuarioDetalhes.getNome());
            usuario.setSobrenome(usuarioDetalhes.getSobrenome());
            usuario.setIdade(usuarioDetalhes.getIdade());
            usuario.setOcupacao(usuarioDetalhes.getOcupacao());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
