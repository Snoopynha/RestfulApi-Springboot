package com.camilly.api.rest.dto;

import com.camilly.api.rest.model.Usuario;

// Classe com métodos que convertem o modelo Usuario e o da DTO
public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setIdade(dto.getIdade());
        usuario.setOcupacao(dto.getOcupacao());
        return usuario;
    }

    public static UsuarioResponse toResponse(Usuario entidade) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setSobrenome(entidade.getSobrenome());
        dto.setIdade(entidade.getIdade());
        dto.setOcupacao(entidade.getOcupacao());
        return dto;
    }
}

