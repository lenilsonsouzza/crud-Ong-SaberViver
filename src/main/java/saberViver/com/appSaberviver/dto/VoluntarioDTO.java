package saberViver.com.appSaberviver.dto;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Voluntario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class VoluntarioDTO {
    @NotBlank(message = "O nome é obrigatorio")
    private String nome;
    private String sobreNome;
    @NotBlank(message = "O Cpf é obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos.")
    private String cpf;
    @NotNull
    private LocalDate dataNascimento;
    private long id;
    @NotBlank(message = "a area de atuação é obrigatorio")
    private String areaAtuacao;
    @NotBlank(message = " email é obrigatorio")
    @Email(message = "E-mail inválido.")
    private String email;
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter 10 ou 11 dígitos.")
    @NotBlank(message = "O telefone é obrigatorio")
    private String telefone;
    private List<Long> atividade = new ArrayList<Long>();
    private String turma;



    public VoluntarioDTO(Voluntario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        sobreNome=entidade.getSobreNome();
        cpf = entidade.getCpf();
        dataNascimento = entidade.getDataNascimento();
        email = entidade.getEmail();
        areaAtuacao = entidade.getAreaAtuacao();
        turma=entidade.getTurma();
        telefone=entidade.getTelefone();

        this.atividade = entidade.getAtividades()
                .stream()
                .map(Atividade::getId)
                .toList();

    }

    public VoluntarioDTO(String nome, String cpf, LocalDate dataNascimento, long id, String areaAtuacao, String email, String turma) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.id = id;
        this.areaAtuacao = areaAtuacao;
        this.email = email;
        this.turma=turma;

    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefonePricipal) {
        this.telefone = telefonePricipal;
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