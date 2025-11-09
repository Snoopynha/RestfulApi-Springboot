package com.camilly.api.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.camilly.api.rest.dto.UsuarioRequest;
import com.camilly.api.rest.dto.UsuarioResponse;
import com.camilly.api.rest.model.Usuario;
import com.camilly.api.rest.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Controlador REST para gerenciar usuários
@RestController
// Mapeia as requisições para a URL base "/usuario"
@RequestMapping("/usuario")
public class UsuarioController {

    // Injeção do serviço de usuários
    private final UsuarioService usuarioService;

    // Construtor para injeção de dependência
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para criar um novo usuário
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioResponse> postUsuario(@RequestBody @Validated UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioNovo = usuarioService.criarUsuario(usuarioRequest);
        URI localizacao = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(usuarioNovo.getId())
                .toUri();
        return ResponseEntity.created(localizacao).body(usuarioNovo);
    }

    // Endpoint para obter todos os usuários
    @GetMapping("/")
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.buscarTodosUsuarios();
        return usuarios.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(usuarios);
    }

    // Endpoint para obter um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario != null
                ? ResponseEntity.ok(usuario)
                : ResponseEntity.notFound().build();
    }

    // Endpoint para atualizar um usuário existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioResponse> putUsuario(@PathVariable Long id,
            @RequestBody UsuarioRequest usuarioDetalhes) {
        UsuarioResponse usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDetalhes);
        return usuarioAtualizado != null
                ? ResponseEntity.ok(usuarioAtualizado)
                : ResponseEntity.notFound().build();
    }

    // Endpoint para deletar um usuário por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        boolean deletado = usuarioService.deletarUsuario(id);
        return deletado
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}