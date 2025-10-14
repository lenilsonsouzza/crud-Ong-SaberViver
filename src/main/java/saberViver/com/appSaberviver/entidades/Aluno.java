package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nome;
    private String sobreNome;
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



}
