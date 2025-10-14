package saberViver.com.appSaberviver.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saberViver.com.appSaberviver.dto.AunteticacaoDTO;
import saberViver.com.appSaberviver.dto.LoginResponseDTO;
import saberViver.com.appSaberviver.dto.RegistroDTO;
import saberViver.com.appSaberviver.entidades.user.User;
import saberViver.com.appSaberviver.repositories.UserRepositorio;
import saberViver.com.appSaberviver.servicos.TokenServer;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuntenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UserRepositorio userRepositorio;
    private final TokenServer tokenServer;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AunteticacaoDTO aunteticacaoDTO) {
        var usernameSenha = new UsernamePasswordAuthenticationToken(aunteticacaoDTO.getLogin(), aunteticacaoDTO.getSenha());
        var auth = this.authenticationManager.authenticate(usernameSenha);
        var token = tokenServer.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    /*
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroDTO dto) {

        // Verifica se login já existe
        if (userRepositorio.findBylogin(dto.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Login já existente!");
        }

        // Recupera o usuário logado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String papelLogado = null;

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            User userLogado = (User) auth.getPrincipal();
            papelLogado = String.valueOf(userLogado.getRoler()); // pega o papel do usuário logado
        }

        // Lógica de autorização
        if ("ADM_MASTER".equals(papelLogado)) {
            // ADM_MASTER pode criar qualquer papel
        } else if ("ADM".equals(papelLogado)) {
            // ADM só pode criar VOLUNTARIO
            if (!"VOLUNTARIO".equalsIgnoreCase(String.valueOf(dto.getRole()))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("ADM só pode criar usuários do tipo VOLUNTARIO");
            }
        } else {
            // Usuário não autorizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para registrar usuários");
        }

        // Criptografa a senha
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        User newUser = new User(dto.getLogin(), senhaCriptografada, dto.getRole());

        userRepositorio.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }
*/
}