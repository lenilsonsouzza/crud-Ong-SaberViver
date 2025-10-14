package saberViver.com.appSaberviver.infra.seguranca;

import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracaoSeguranca {

    private final FiltroDeSeguranca filtroDeSeguranca;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // para H2 Console
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // 🔓 Rotas públicas (sem login)
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/alunos/publico/**").permitAll() // cadastro público de aluno
                        .requestMatchers(HttpMethod.GET, "/atividades/publico/**").permitAll() // listar atividades para público

                        // 👨‍🎓 VOLUNTÁRIO — logado
                        .requestMatchers(HttpMethod.POST, "/alunos/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER") // inserir aluno
                        .requestMatchers(HttpMethod.PUT, "/alunos/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER") // editar aluno
                        .requestMatchers(HttpMethod.GET, "/alunos/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER") // listar/buscar aluno
                        .requestMatchers(HttpMethod.GET, "/atividades/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER") // listar atividades
                        .requestMatchers(HttpMethod.POST, "/atividades/**").hasAnyRole("VOLUNTARIO", "ADM", "ADM_MASTER") // criar atividades

                        // 👨‍💼 ADMINISTRADOR — logado
                        .requestMatchers("/voluntarios/**").hasAnyRole("ADM", "ADM_MASTER") // gerenciar voluntários
                        .requestMatchers(HttpMethod.DELETE, "/alunos/**").hasAnyRole("ADM", "ADM_MASTER") // remover alunos
                        .requestMatchers(HttpMethod.DELETE, "/atividades/**").hasAnyRole("ADM", "ADM_MASTER") // remover atividades

                        // 👑 ADM MASTER — acesso total
                        .requestMatchers("/adm/**").hasRole("ADM_MASTER") // gerenciar administradores
                        .requestMatchers("/registrar/adm/**").hasRole("ADM_MASTER") // registrar administradores
                        .requestMatchers("/registrar/voluntario/**").hasAnyRole("ADM", "ADM_MASTER") // registrar voluntários

                        // 🔐 Qualquer outra rota exige autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroDeSeguranca, UsernamePasswordAuthenticationFilter.class)
                .build();
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