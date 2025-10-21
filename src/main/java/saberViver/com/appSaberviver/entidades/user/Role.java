package saberViver.com.appSaberviver.entidades.user;

public enum Role {


    ALUNO("ALUNO"),
    VOLUNTARIO("VOLUNTARIO"),
    ADMIN("ADMIN"),
    ADM_MASTER("ADM_MASTER");
    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
