package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "tb_colaborador")
public class Colaborador extends Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    Id;
    private String funcao;

    @ManyToMany
    @JoinTable(
            name = "tb_colaborador_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "colaborador_id"), // FK colaborador
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
    private List<Atividade> atividades = new ArrayList<>();

public Colaborador(){

}

    public Colaborador( long id,String nome, String email, int senha, int cpf, LocalDate dataNascimento,
                        String funcao) {
        super(nome, email, senha, cpf, dataNascimento);
       this.Id=id;
       this.funcao = funcao;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }
    public String getFuncao() {
        return funcao;
    }

      public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public long getId() {
        return Id;
    }
}
