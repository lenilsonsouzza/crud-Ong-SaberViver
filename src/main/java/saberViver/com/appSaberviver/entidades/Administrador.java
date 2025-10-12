package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_administrador")
public class Administrador extends Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String email;
    String areaAtuacao;


    public Administrador(String nome, String senha, String cpf, LocalDate dataNascimento, String telefone, long id, String areaAtuacao,
                         String email) {
        super(nome, senha, cpf, dataNascimento, telefone);
        this.id = id;
        this.email=email;
        this.areaAtuacao=areaAtuacao;

    }

}