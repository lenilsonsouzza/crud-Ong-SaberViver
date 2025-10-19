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

        String path = request.getRequestURI();

        // 🟢 Ignora rotas públicas (sem autenticação)
        if (path.startsWith("/auth/login") ||
                path.startsWith("/alunos/publico") ||
                path.startsWith("/atividades/publico") ||
                path.startsWith("/h2-console")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 🔐 Processa token normalmente para rotas protegidas
        String token = recoverToken(request);
        if (token != null) {
            try {
                String login = tokenServer.validarToken(token);
                if (login != null) {
                    UserDetails user = userRepositorio.findBylogin(login);
                    if (user != null) {
                        var autenticacao = new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(autenticacao);
                    } else {
                        System.out.println("Usuário não encontrado para login: " + login);
                    }
                } else {
                    System.out.println("Token inválido ou expirado");
                }
            } catch (Exception e) {
                System.out.println("Erro ao validar token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    // 🧩 Método auxiliar que extrai o token JWT do cabeçalho Authorization
    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
