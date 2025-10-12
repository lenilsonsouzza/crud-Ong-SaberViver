package saberViver.com.appSaberviver.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class ControleDeAuntenticacao {

    private final AuthenticationManager authenticationManager;
    private final UserRepositorio userRepositorio;
    private final TokenServer tokenServer;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AunteticacaoDTO aunteticacaoDTO) {
        var UsernameSenha = new UsernamePasswordAuthenticationToken(aunteticacaoDTO.getLogin(), aunteticacaoDTO.getSenha());
        var auth = this.authenticationManager.authenticate(UsernameSenha);
        var token = tokenServer.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroDTO dto) {
        if (this.userRepositorio.findBylogin(dto.getLogin()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getSenha());
        User newUser = new User(dto.getLogin(), encryptedPassword, dto.getPapel());
        this.userRepositorio.save(newUser);
        return ResponseEntity.ok().build();

    }
}
