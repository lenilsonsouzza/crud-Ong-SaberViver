package saberViver.com.appSaberviver.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saberViver.com.appSaberviver.dto.AunteticacaoDTO;
import saberViver.com.appSaberviver.dto.LoginResponseDTO;
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


}