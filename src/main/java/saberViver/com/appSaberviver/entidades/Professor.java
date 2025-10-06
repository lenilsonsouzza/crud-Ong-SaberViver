package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "tb_professor")
public class Professor extends Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String email;
    private String areaAtuacao;
    private String turma;



    @ManyToMany
    @JoinTable(
            name = "tb_professor_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "professor_id"), // FK colaborador
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
    private List<Atividade> atividades = new ArrayList<>();

    public Professor() {

    }

    public Professor(String nome, String senha, String cpf, LocalDate dataNascimento,String telefone, long id,
                     String areaAtuacao, String email,String turma) {
        super(nome, senha, cpf, dataNascimento,telefone);
        this.Id = id;
        this.areaAtuacao = areaAtuacao;
        this.email = email;
        this.turma=turma;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public String getEmail() {
        return email;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Professor{" +
                super.toString()+
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                ", turma='" + turma + '\'' +
                ", atividades=" + atividades +
                '}';
    }
}
