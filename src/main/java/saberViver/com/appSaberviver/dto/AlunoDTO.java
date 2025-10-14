package saberViver.com.appSaberviver.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlunoDTO {

    private long Id;
    @NotBlank(message = " O nome é Obrigatorio")
    private String nome;
    private String sobreNome;
    private String apelido;
    private String cpf;
    @NotNull(message = "A data de nascimento é obrigatorio")
    private LocalDate dataNascimento;
    @NotBlank(message = "O nome do Responsavel é obrigatorio")
    private String nomeResponsavel;
    @NotBlank(message = "O CPF do responsável é obrigatório.")
    private String cpfResponsavel;
    @NotBlank(message = "O telefone principal é obrigatório.")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone principal deve ter 10 ou 11 dígitos.")
    private String telefonePrincipal;
    @Pattern(regexp = "\\d{10,11}", message = "O telefone secundário deve ter 10 ou 11 dígitos.")
    private String telefoneOpcional;
    @NotEmpty(message = "O aluno deve estar vinculado a pelo menos uma atividade.")
    private List<Long> atividade = new ArrayList<Long>();
    @AssertTrue(message = "É obrigatório autorizar o termo para concluir o cadastro.")
    private boolean termoAutorizado;


    public AlunoDTO(Aluno entidade) {
        Id = entidade.getId();
        nome = entidade.getNome();
        sobreNome=entidade.getSobreNome();
        cpf = entidade.getCpf();
        apelido = entidade.getApelido();
        nomeResponsavel = entidade.getNomeResponsavel();
        dataNascimento = entidade.getDataNascimento();
        cpfResponsavel = entidade.getCpfResponsavel();
        telefonePrincipal = entidade.getTelefonePrincipal();
        this.atividade = entidade.getAtividades()
                .stream()
                .map(Atividade::getId)
                .toList();
        termoAutorizado = entidade.isTermoAutorizado();
    }


    public AlunoDTO(Long id, String nome, String nomeResponsavel, String telefonePrincipal) {
        this.Id = id;
        this.nome = nome;
        this.nomeResponsavel = nomeResponsavel;
        this.telefonePrincipal = telefonePrincipal;
    }


    public List<Long> getAtividade() {
        return atividade;
    }


    public AlunoDTO(long id, String nome, String sobreNome, String apelido, String cpf, LocalDate dataNascimento, String nomeResponsavel,
                    String cpfResponsavel, String telefonePrincipal, String telefoneOpcional, List<Long> atividade) {
        Id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.telefonePrincipal = telefonePrincipal;
        this.telefoneOpcional = telefoneOpcional;
        this.atividade = atividade;
    }


}
