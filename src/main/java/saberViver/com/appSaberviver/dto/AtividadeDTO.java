package saberViver.com.appSaberviver.dto;


import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;

public class AtividadeDTO {
    private long Id;
    private String nome;
    private String descricao;




    public AtividadeDTO(){

    }

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
