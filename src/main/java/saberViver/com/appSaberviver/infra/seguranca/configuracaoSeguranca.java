package saberViver.com.appSaberviver.infra.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class configuracaoSeguranca {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/h2-console/**").permitAll()

                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/registrar").permitAll()

                        //  Rotas públicas cadastro de aluno no site)
                        .requestMatchers(HttpMethod.POST, "/inserir", "/alunos/publico/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/atividades/publico/**").permitAll()

                        // libera o console H2

                        //         Aluno (apenas logado, qualquer papel)
                        .requestMatchers("/alunos/**").hasAnyRole("ALUNO", "VOLUNTARIO", "ADM", "ADM_MASTER")

                        //          Voluntário — pode criar/editar alunos e atividades
                        .requestMatchers(HttpMethod.POST, "/atividades/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER")
                        .requestMatchers(HttpMethod.PUT, "/alunos/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER")

                        //           Administrador — pode gerenciar voluntários e alunos
                        .requestMatchers("/voluntarios/**").hasAnyRole("ADM", "ADM_MASTER")
                        .requestMatchers(HttpMethod.DELETE, "/alunos/**").hasAnyRole("ADM", "ADM_MASTER")

                        //         ADM MASTER — tem acesso total (exemplo: gerenciar outros administradores)
                        .requestMatchers("/adm/**").hasRole("ADM_MASTER")
                        //  Todas as outras rotas precisam estar autenticadas
                        .anyRequest().authenticated()

                ).build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
