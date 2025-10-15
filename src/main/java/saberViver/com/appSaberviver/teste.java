package saberViver.com.appSaberviver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Usuario;
import saberViver.com.appSaberviver.entidades.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaCriptografada = encoder.encode("30825011");
            System.out.println(senhaCriptografada);
        }
    }

