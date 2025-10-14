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
@MappedSuperclass
public abstract class Usuario {


    private String nome;
    private String sobreNome;
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;


}
