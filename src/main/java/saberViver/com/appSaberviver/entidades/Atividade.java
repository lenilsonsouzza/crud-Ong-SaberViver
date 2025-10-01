package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAtividade;
    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "atividades")
    private List<Aluno> alunos;

    @ManyToMany(mappedBy = "atividades")
    private List<Colaborador> colaboradores;
public Atividade(){

}
    public Atividade(long idAtividade,String nome, String descricao) {
        this.idAtividade=idAtividade;
        this.nome = nome;
        this.descricao = descricao;
    }

    public long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(long idAtividade) {
        this.idAtividade = idAtividade;
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


}
