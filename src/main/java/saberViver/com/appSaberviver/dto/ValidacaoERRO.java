package saberViver.com.appSaberviver.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidacaoERRO extends CustomError{
    private List<mensagemCampos> erros = new ArrayList<>();

    public List<mensagemCampos> getErros() {
        return erros;
    }

    public ValidacaoERRO(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }
    public void addError(String  nomeCampo,String mensagem){
        erros.add( new mensagemCampos(nomeCampo,mensagem));
    }
}
