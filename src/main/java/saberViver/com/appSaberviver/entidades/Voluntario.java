package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import saberViver.com.appSaberviver.entidades.user.User;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "tb_voluntario")
public class Voluntario extends Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String email;
    private String areaAtuacao;
    private String turma;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToMany
    @JoinTable(
            name = "tb_voluntario_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "voluntario_id"), // FK colaborador
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
    private List<Atividade> atividades = new ArrayList<>();

    public Voluntario() {

    }

    public Voluntario(String nome, String sobreNome, String cpf, LocalDate dataNascimento, String telefone, long id,
                      String areaAtuacao, String email, String turma) {
        super(nome, sobreNome, cpf, dataNascimento, telefone);
        this.Id = id;
        this.areaAtuacao = areaAtuacao;
        this.email = email;
        this.turma = turma;
    }


}
