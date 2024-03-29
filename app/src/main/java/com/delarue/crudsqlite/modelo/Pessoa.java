package com.delarue.crudsqlite.modelo;

import java.io.Serializable;

public class Pessoa implements Serializable {

    // Classe Pojo

    private int id;
    private String nome;
    private int idade;
    private String endereco;
    private String telefone;

    // Construtor Vazio
    public Pessoa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return   //id + "\n" +
                "Nome: " + nome  +"\n" +
                "Idade: " + idade + " Anos"+"\n" +
                "Endereco: " + endereco +"\n" +
                "Telefone: " + telefone;
    }

}
