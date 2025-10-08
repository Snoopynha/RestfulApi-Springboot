package com.camilly.api.rest.model;

import jakarta.persistence.*;

@Entity
@Table  (name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String sobrenome;
    private int idade;
    private String ocupacao;

    // Construtores
    public Usuario() {}

    public Usuario(long id, String nome, String sobrenome, int idade, String ocupacao){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.ocupacao = ocupacao;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    @Override
    public String toString(){
        return "Usuário [id=" + id + ", nome=" + nome + ", sobrenome="
        + sobrenome + ", idade=" + idade + ", ocupação=" + ocupacao + "]";
    }
}
