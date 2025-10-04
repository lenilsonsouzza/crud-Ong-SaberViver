package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_aluno")
public class Aluno extends Usuario{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nomeResponsavel;
    private String cpfResponsavel;

    @ManyToMany
    @JoinTable(
            name = "tb_aluno_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "aluno_id"), // FK aluno
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
    private List<Atividade> atividades = new ArrayList<>();
public Aluno(){

}
    public Aluno(long id, String nome,int senha, int cpf, LocalDate dataNascimento, String nomeResponsavel, String cpfResponsavel) {
        super(nome,senha, cpf, dataNascimento);
        this.Id=id;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
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
    public long getId() {
        return Id;
    }


}
