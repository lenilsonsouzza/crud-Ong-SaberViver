package saberViver.com.appSaberviver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saberViver.com.appSaberviver.entidades.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CadastroVoluntarioDTO {
    @NotBlank(message = "O Nome é obrigatorio!")
    private String nome;
    @NotBlank(message = "o sobreNome é obrigatorio!")
    private String sobreNome;
    @NotBlank(message = "O Cpf é obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos.")
    private String cpf;
    @NotNull
    private LocalDate dataNascimento;
    private long id;
    @NotBlank(message = "a area de atuação é obrigatorio")
    private String areaAtuacao;
    @NotBlank(message = " email é obrigatorio")
    @Email(message = "E-mail inválido.")
    private String email;
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter 10 ou 11 dígitos.")
    @NotBlank(message = "O telefone é obrigatorio")
    private String telefone;
    private List<Long> atividade = new ArrayList<Long>();
    private String turma;
    private String login;
    private String senha;



}
