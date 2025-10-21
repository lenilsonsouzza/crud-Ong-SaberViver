package saberViver.com.appSaberviver.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.entidades.Voluntario;
import saberViver.com.appSaberviver.entidades.user.Role;
import saberViver.com.appSaberviver.entidades.user.User;
import saberViver.com.appSaberviver.repositories.AdministradorRepositorio;
import saberViver.com.appSaberviver.repositories.UserRepositorio;
import saberViver.com.appSaberviver.repositories.VoluntarioRepositorio;

import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UserRepositorio userRepositorio;


    private final VoluntarioRepositorio voluntarioRepositorio;


    private final AdministradorRepositorio administradorRepositorio;

    @GetMapping("/me")
    public ResponseEntity<?> getUsuarioAutenticado(@AuthenticationPrincipal User userAutenticado) {
        if (userAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        // Busca o User completo no banco (caso precise de mais dados)
        Optional<User> user = userRepositorio.findByLogin(userAutenticado.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        Role role = user.get().getRole();

        // ADM_MASTER → retorna apenas os dados básicos
        if (role == Role.ADM_MASTER) {
            return ResponseEntity.ok(user.get());
        }

        // ADMINISTRADOR → retorna os dados completos do Administrador
        if (role == Role.ADMIN) {
            Administrador adm = administradorRepositorio.findByUser(user.get());
            if (adm == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado");
            }
            return ResponseEntity.ok(adm);
        }

        // VOLUNTARIO → retorna os dados completos do Voluntario
        if (role == Role.VOLUNTARIO) {
            Voluntario vol = voluntarioRepositorio.findByUser(user.get());
            if (vol == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voluntário não encontrado");
            }
            return ResponseEntity.ok(vol);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de usuário não reconhecido");
    }
}