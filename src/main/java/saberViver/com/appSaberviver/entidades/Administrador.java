package saberViver.com.appSaberviver.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saberViver.com.appSaberviver.entidades.user.User;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_administrador")
public class Administrador extends Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String email;
    String areaAtuacao;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Administrador(String nome,String sonbreNome,String cpf, LocalDate dataNascimento, String telefone, long id, String areaAtuacao,
                         String email) {
        super(nome,sonbreNome,cpf, dataNascimento, telefone);
        this.id = id;
        this.email=email;
        this.areaAtuacao=areaAtuacao;

    }

}