package saberViver.com.appSaberviver.infra.seguranca;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import saberViver.com.appSaberviver.repositories.UserRepositorio;
import saberViver.com.appSaberviver.servicos.TokenServer;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {

    private final TokenServer tokenServer;
    private final UserRepositorio userRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            try {
                var login = tokenServer.validarToken(token);
                if (login != null) {
                    UserDetails user = userRepositorio.findBylogin(login);

                    if (user != null) {
                        var autenticacao = new UsernamePasswordAuthenticationToken
                                (user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(autenticacao);
                    } else {
                        System.out.println(" Usuario não encontrado para Login: " + login);
                    }
                } else {
                    System.out.println("token invalido ou expirado");
                }
            } catch (Exception e) {
                System.out.println("Erro ao validar token " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}
