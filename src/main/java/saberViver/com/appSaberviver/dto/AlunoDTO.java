package saberViver.com.appSaberviver.dto;


import saberViver.com.appSaberviver.entidades.Aluno;


import java.time.LocalDate;
import java.util.List;

public class AlunoDTO {
    private long Id;
    private String nome;
    private String apelido;
    private String cpf;
    private LocalDate dataNascimento;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String telefonePrincipal;
    private String telefoneOpcional;
    private List<AtividadeDTO> atividade;


    public AlunoDTO() {

    }

    public AlunoDTO(Long id, String nome, String nomeResponsavel, String telefonePrincipal) {
        this.Id = id;
        this.nome = nome;
        this.nomeResponsavel = nomeResponsavel;
        this.telefonePrincipal = telefonePrincipal;
    }

    public AlunoDTO(Aluno entidade) {
        Id = entidade.getId();
        nome = entidade.getNome();
        cpf = entidade.getCpf();
        apelido = entidade.getApelido();
        nomeResponsavel = entidade.getNomeResponsavel();
        dataNascimento = entidade.getDataNascimento();
        cpfResponsavel = entidade.getCpfResponsavel();
        telefonePrincipal = entidade.getTelefonePrincipal();
        atividade = entidade.getAtividades().stream().map(AtividadeDTO::new).toList();

    }

    public AlunoDTO(long id, String nome, String apelido, String cpf, LocalDate dataNascimento, String nomeResponsavel,
                    String cpfResponsavel, String telefonePrincipal, String telefoneOpcional, List<AtividadeDTO> atividade) {
        Id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.telefonePrincipal = telefonePrincipal;
        this.telefoneOpcional = telefoneOpcional;
        this.atividade = atividade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }


    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public long getId() {
        return Id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }
}
