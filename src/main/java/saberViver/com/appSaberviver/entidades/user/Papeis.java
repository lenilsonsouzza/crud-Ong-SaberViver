package saberViver.com.appSaberviver.entidades.user;

public enum Papeis {


    ALUNO("aluno"),
    VOLUNTARIO("voluntario"),
    ADM("admin"),
    ADM_Master("admMaster");
    private String papel;

    Papeis(String papel) {
        this.papel = papel;
    }

    public String getPapel() {
        return papel;
    }
}
