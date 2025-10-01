package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_colaborador")
public class Colaborador extends Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idColab;
    private String funcao;

    @ManyToMany
    @JoinTable(
            name = "tb_colaborador_atividade", // tabela intermediária
            joinColumns = @JoinColumn(name = "colaborador_id"), // FK colaborador
            inverseJoinColumns = @JoinColumn(name = "atividade_id") // FK atividade
    )
       private List<Atividade> atividades;

public Colaborador(){

}
    public Colaborador(long idColab, String nome, String email, int senha, int cpf, LocalDate dataNascimento,
                       TipoUsuario tipoUsuario, String funcao, List<Atividade> atividades) {
        super(nome, email, senha, cpf, dataNascimento);
       this.idColab=idColab;
        this.funcao = funcao;
        this.atividades=atividades;
    }
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    public long getIdColab() {
        return idColab;
    }

    public void setIdColab(long idColab) {
        this.idColab = idColab;
    }
}
