package saberViver.com.appSaberviver.dto;

import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDTO {

    private String nome;
    private String senha;
    private String cpf;
    private LocalDate dataNascimento;
    private long id;
    private String areaAtuacao;
    private String email;
    private String telefonePricipal;
    private List<Long> atividade = new ArrayList<Long>();
    private String turma;



    public ProfessorDTO(Professor entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        cpf = entidade.getCpf();
        dataNascimento = entidade.getDataNascimento();
        email = entidade.getEmail();
        areaAtuacao = entidade.getAreaAtuacao();
        turma=entidade.getTurma();
        telefonePricipal=entidade.getTelefone();

        this.atividade = entidade.getAtividades()
                .stream()
                .map(Atividade::getId)
                .toList();

    }


    public ProfessorDTO() {

    }



    public ProfessorDTO(String nome, String senha, String cpf, LocalDate dataNascimento, long id, String areaAtuacao, String email, String turma) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.id = id;
        this.areaAtuacao = areaAtuacao;
        this.email = email;
        this.turma=turma;
    }
    public String getTelefonePricipal() {
        return telefonePricipal;
    }

    public void setTelefonePricipal(String telefonePricipal) {
        this.telefonePricipal = telefonePricipal;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public List<Long> getAtividade() {
        return atividade;
    }

    public void setAtividade(List<Long> atividade) {
        this.atividade = atividade;
    }

    public String getNome() {
        return nome;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}