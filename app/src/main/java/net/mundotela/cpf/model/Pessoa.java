package net.mundotela.cpf.model;

/**
 * Created by Henrique on 19/06/2017.
 */

public class Pessoa {// classe auxilia para manipulação dos dados

    private long id;
    private String nome;
    private String cpf;
    private String idade;
    private String telefone;
    private String email;

    public Pessoa() {//construtor vazio

    }

    public Pessoa(long id, String nome, String cpf, String idade, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
    }// contrutor comleto

    //metodos geters e seters

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
