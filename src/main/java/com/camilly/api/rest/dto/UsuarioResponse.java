package com.camilly.api.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Classe do DTO de saída
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String sobrenome;
    private int idade;
    private String ocupacao;
}
