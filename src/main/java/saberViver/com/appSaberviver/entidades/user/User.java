package saberViver.com.appSaberviver.entidades.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Entity(name = "users")
@Table(name = "tb_users")
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
    private Role roler;

    public User (String login, String senha, Role role){
        this.login=login;
        this.senha=senha;
        this.roler =role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.roler == Role.ADM_MASTER) return List.of(
                new SimpleGrantedAuthority("ROLE_"+ Role.ADM_MASTER.getRoler().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+ Role.ADM.getRoler().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+ Role.VOLUNTARIO.getRoler().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+ Role.ALUNO.getRoler().toUpperCase()));

        else if (this.roler == Role.ADM) return List.of(
                new SimpleGrantedAuthority("ROLE_"+ Role.ADM.getRoler().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+ Role.VOLUNTARIO.getRoler().toUpperCase()),
                new SimpleGrantedAuthority("ROLE_"+ Role.ALUNO.getRoler().toUpperCase()));

        else if (this.roler == Role.VOLUNTARIO)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_"+ Role.VOLUNTARIO.getRoler().toUpperCase()),
                    new SimpleGrantedAuthority("ROLE_"+ Role.ALUNO.getRoler().toUpperCase()));

        else return List.of(
                new SimpleGrantedAuthority("ROLE_"+ Role.ALUNO.getRoler().toUpperCase()));
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
