package saberViver.com.appSaberviver.entidades.user;

public enum Roles {


    ALUNO("ALUNO"),
    VOLUNTARIO("VOLUNTARIO"),
    ADM("ADM"),
    ADM_MASTER("ADM_MASTER");
    private String roler;

    Roles(String roler) {
        this.roler = roler;
    }

    public String getRoler() {
        return roler;
    }
}
