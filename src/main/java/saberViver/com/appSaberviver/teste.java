package saberViver.com.appSaberviver;

import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        Atividade atividade = new Atividade(1, "Capoeira", "treino capoeira na rua");
        Atividade atividade2 = new Atividade(2, "dança", "tserewrereino");
        Voluntario professor = new Voluntario();
        Aluno aluno = new Aluno();
        aluno.setNome("Rafel");
        aluno.setCpf("3434324");
        professor.setNome("lenilson");
        professor.setAreaAtuacao("matematica");

        List<Atividade> atividadeProf = new ArrayList<>();
        atividadeProf.add(atividade);
        atividadeProf.add(atividade2);
        professor.setAtividades(atividadeProf);
        System.out.println(professor);
    }
}
