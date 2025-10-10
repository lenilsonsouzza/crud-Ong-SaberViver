package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
public class Aluno  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nome;
    private String apelido;
    private String cpf;
    private LocalDate dataNascimento;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String telefonePrincipal;
    private String telefoneOpcional;
    private boolean termoAutorizado;


    @ManyToMany
    @JoinTable(
            name = "tb_aluno_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "aluno_id"), // FK aluno
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
    private List<Atividade> atividades = new ArrayList<>();

    public Aluno(long id, String nome, String apelido, String cpf, LocalDate dataNascimento, String nomeResponsavel,
                 String cpfResponsavel, String telefonePrincipal, String telefoneOpcional, List<Atividade> atividades,boolean termoAutorizado) {
        Id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.telefonePrincipal = telefonePrincipal;
        this.telefoneOpcional = telefoneOpcional;
        this.atividades = atividades;
        this.termoAutorizado=termoAutorizado;

    }

    @PreRemove
    private void removerRelacionamentos() {
        for (Atividade atividade : atividades) {
            atividade.getAlunos().remove(this);
        }
        atividades.clear();
    }

    public boolean isTermoAutorizado() {
        return termoAutorizado;
    }

    public void setTermoAutorizado(boolean termoAutorizado) {
        this.termoAutorizado = termoAutorizado;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
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

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelefoneOpcional() {
        return telefoneOpcional;
    }

    public void setTelefoneOpcional(String telefoneOpcional) {
        this.telefoneOpcional = telefoneOpcional;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Aluno() {

    }
}
