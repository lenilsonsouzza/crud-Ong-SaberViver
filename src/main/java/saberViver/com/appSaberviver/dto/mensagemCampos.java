package saberViver.com.appSaberviver.dto;

public class mensagemCampos {
    private String nomeCampo;
    private String mensagem;

    public mensagemCampos(String nomeCampo, String mensagem) {
        this.nomeCampo = nomeCampo;
        this.mensagem = mensagem;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
