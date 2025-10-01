package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
public class Aluno extends Usuario{
    public long getIdAluno() {
        return idAluno;
    }



    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAluno;
    private String nomeResponsavel;
    private String cpfResponsavel;

    @ManyToMany
    @JoinTable(
            name = "tb_aluno_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "aluno_id"), // FK aluno
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
    private List<Atividade> atividades;
public Aluno(){

}
    public Aluno(long idAluno, String nome, String email, int senha, int cpf, LocalDate dataNascimento, String nomeResponsavel, String cpfResponsavel, List<Atividade> atividades) {
        super(nome, email, senha, cpf, dataNascimento);
        this.idAluno=idAluno;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.atividades = atividades;
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
    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
