package saberViver.com.appSaberviver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saberViver.com.appSaberviver.entidades.Administrador;


import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDTO {
    private long id;
    private String nome;
    private String cpf;
    private String senha;
    private String telefone;
    private String areaAtuacao;
    private String email;
    private LocalDate dataNascimento;


    public AdministradorDTO(Administrador entidade){
        id=entidade.getId();
        nome=entidade.getNome();
        cpf=entidade.getCpf();
        senha=entidade.getSenha();
        telefone=entidade.getTelefone();
        areaAtuacao=entidade.getAreaAtuacao();
        email=entidade.getEmail();
        dataNascimento=entidade.getDataNascimento();
    }

}
