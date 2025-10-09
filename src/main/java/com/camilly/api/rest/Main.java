package com.camilly.api.rest;

import com.camilly.api.rest.model.Usuario;

public class Main {
    public static void main(String[] args) {
        // Teste de criação de um usuário
        Usuario usuario = new Usuario(0, "Lilia", "Calderu", 450, "se colocar em situações de perigo");
        System.out.println(usuario.toString());
    }
}
