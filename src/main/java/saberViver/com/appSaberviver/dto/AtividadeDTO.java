package saberViver.com.appSaberviver.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

}
