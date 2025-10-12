package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import saberViver.com.appSaberviver.entidades.user.User;

import java.time.LocalDate;

@MappedSuperclass
public abstract class  Usuario {
    private String nome;
    @Column(unique = true)
    private String senha;
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;


    public Usuario() {

    }


    public Usuario(String nome, String senha, String cpf, LocalDate dataNascimento, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;


    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


}
