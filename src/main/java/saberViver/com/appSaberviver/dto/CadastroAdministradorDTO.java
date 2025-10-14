package saberViver.com.appSaberviver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
public class CadastroAdministradorDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O SobreNome é obrigatório")
    private String sobreNome;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos.")
    private String cpf;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter 10 ou 11 dígitos.")
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "A área de atuação é obrigatória")
    private String areaAtuacao;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "E-mail inválido.")
    private String email;

    private LocalDate dataNascimento;


    @NotBlank(message = "O login é obrigatório")
    private String login;
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6,max = 8, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;




    public CadastroAdministradorDTO(String nome, String sobreNome, String cpf, String telefone, String areaAtuacao, String email, LocalDate dataNascimento, String login, String senha) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.areaAtuacao = areaAtuacao;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
    }
}

