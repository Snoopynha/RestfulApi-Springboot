package com.camilly.api.rest.contoller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.camilly.api.rest.model.Usuario;
import com.camilly.api.rest.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> postUsuario(@RequestBody @Validated Usuario usuario) {
        Usuario usuarioNovo = usuarioService.criarUsuario(usuario);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(usuarioNovo.getId()).toUri();
        return ResponseEntity.created(localizacao).body(usuarioNovo);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return usuarios.isEmpty()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    public Usuario getUsuario(@RequestParam Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> putUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalhes) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDetalhes);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }
}