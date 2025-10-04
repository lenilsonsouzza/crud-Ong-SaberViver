package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    @ManyToMany(mappedBy = "atividades")
    private List<Colaborador> colaboradores = new ArrayList<>();

    public Atividade() {

    }


    public Atividade(long id, String nome, String descricao) {
        this.Id = id;
        this.nome = nome;
        this.descricao = descricao;
    }


    public List<Aluno> getAlunos() {
        return alunos;
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

    public String getDiscricao() {
        return descricao;
    }

    public void setDiscricao(String discricao) {
        this.descricao = discricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
