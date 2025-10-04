package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;
@MappedSuperclass
public class Usuario {
        private String nome;
        @Column(unique = true)
        private String email;
        private int senha;
    @Column(unique = true)
    private int cpf;
    private LocalDate dataNascimento;


    public Usuario(){

    }


    public Usuario( String nome, String email, int senha, int cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;

    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


}
