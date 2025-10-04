package saberViver.com.appSaberviver.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import saberViver.com.appSaberviver.entidades.Aluno;

import java.time.LocalDate;

public class AlunoDTO {
    private long Id;
    private String nome;
    private int senha;
    private int cpf;
    private LocalDate dataNascimento;

    public AlunoDTO() {

    }

    public AlunoDTO(Aluno aluno) {
        Id = aluno.getId();
        nome = aluno.getNome();
        senha= aluno.getSenha();
        cpf= aluno.getCpf();
        dataNascimento= aluno.getDataNascimento();
    }


    public AlunoDTO(long id, String nome, int senha, int cpf, LocalDate dataNascimento, String nomeResponsavel, String cpfResponsavel) {
        Id = id;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    private String nomeResponsavel;
    private String cpfResponsavel;

    public String getNome() {
        return nome;
    }

    public int getSenha() {
        return senha;
    }

    public int getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public long getId() {
        return Id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }
}
