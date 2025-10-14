package saberViver.com.appSaberviver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "O nome é obrigatorio")
    private String nome;
    private String sobreNome;
    @NotBlank(message = "O Cpf é obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos.")
    private String cpf;
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter 10 ou 11 dígitos.")
    @NotBlank(message = "O telefone é obrigatorio")
    private String telefone;
    @NotBlank(message = "a area de atuação é obrigatorio")
    private String areaAtuacao;
    @NotBlank(message = " email é obrigatorio")
    @Email(message = "E-mail inválido.")
    private String email;
    private LocalDate dataNascimento;


    public AdministradorDTO(Administrador entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        sobreNome=entidade.getSobreNome();
        cpf = entidade.getCpf();
        telefone = entidade.getTelefone();
        areaAtuacao = entidade.getAreaAtuacao();
        email = entidade.getEmail();
        dataNascimento = entidade.getDataNascimento();
    }

}
