package saberViver.com.appSaberviver.entidades.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Papeis papel;

    public User (String login, String senha, Papeis papel){
        this.login=login;
        this.senha=senha;
        this.papel=papel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.papel == Papeis.ADM_Master) return List.of(
                new SimpleGrantedAuthority("ROLE_"+Papeis.ADM_Master.getPapel().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+Papeis.ADM.getPapel().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+Papeis.VOLUNTARIO.getPapel().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+Papeis.ALUNO.getPapel().toUpperCase()));

        else if (this.papel == Papeis.ADM) return List.of(
                new SimpleGrantedAuthority("ROLE_"+Papeis.ADM.getPapel().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+Papeis.VOLUNTARIO.getPapel().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+ Papeis.ALUNO.getPapel().toUpperCase()));

        else if (this.papel == Papeis.VOLUNTARIO)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_"+Papeis.VOLUNTARIO.getPapel().toUpperCase()),
                    new SimpleGrantedAuthority("ROLE_"+Papeis.ALUNO.getPapel().toUpperCase()));

        else return List.of(
                new SimpleGrantedAuthority("ROLE_"+Papeis.ALUNO.getPapel().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
