package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricao;


    @ManyToMany(mappedBy = "atividades")
    private List<Aluno> alunos = new ArrayList<>();


    @ManyToMany(mappedBy = "atividades")
    private List<Professor> professores = new ArrayList<>();


    public Atividade(long id, String nome, String descricao) {
        this.Id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public long getId() {
        return Id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
