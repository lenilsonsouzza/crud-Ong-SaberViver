package saberViver.com.appSaberviver.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saberViver.com.appSaberviver.dto.UsuarioMeResponseDTO;
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepositorio
                .findByLogin(userAutenticado.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Role role = user.getRole();

        // ADM_MASTER
        if (role == Role.ADM_MASTER) {
            return ResponseEntity.ok(
                    new UsuarioMeResponseDTO(
                            user.getLogin(),
                            role,
                            null,
                            null,
                            "Administrador Master"
                    )
            );
        }

        // ADMINISTRADOR
        if (role == Role.ADM) {
            Administrador adm = administradorRepositorio.findByUser(user);
            return ResponseEntity.ok(
                    new UsuarioMeResponseDTO(
                            user.getLogin(),
                            role,
                            null,
                            adm.getId(),
                            adm.getNome()
                    )
            );
        }

        // VOLUNTARIO
        if (role == Role.VOLUNTARIO) {
            Voluntario vol = voluntarioRepositorio.findByUser(user);
            return ResponseEntity.ok(
                    new UsuarioMeResponseDTO(
                            user.getLogin(),
                            role,
                            vol.getId(),
                            null,
                            vol.getNome()
                    )
            );
        }

        return ResponseEntity.badRequest().build();
    }

}