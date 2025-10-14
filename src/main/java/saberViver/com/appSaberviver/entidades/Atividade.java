package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Voluntario> voluntarios = new ArrayList<>();




    @PreRemove
    public void preRemove() {
        for (Aluno aluno : new ArrayList<>(alunos)) {
            aluno.getAtividades().remove(this);
        }
        alunos.clear();

        for (Voluntario voluntario : new ArrayList<>(voluntarios)) {
            voluntario.getAtividades().remove(this);
        }
        voluntarios.clear();
    }



}
