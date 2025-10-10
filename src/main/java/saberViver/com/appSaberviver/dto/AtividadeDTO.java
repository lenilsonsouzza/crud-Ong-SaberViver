package saberViver.com.appSaberviver.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
@NoArgsConstructor
public class AtividadeDTO {
    private long Id;
    @NotBlank
    private String nome;
    private String descricao;




public AtividadeDTO(Atividade entidade){
        Id=entidade.getId();
        nome=entidade.getNome();
        descricao=entidade.getDescricao();
}
    public AtividadeDTO(long id, String nome, String descricao) {
        Id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public long getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
