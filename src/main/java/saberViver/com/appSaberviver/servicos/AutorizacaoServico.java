package saberViver.com.appSaberviver.servicos;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import saberViver.com.appSaberviver.repositories.UserRepositorio;

@RequiredArgsConstructor
@Service
public class AutorizacaoServico implements UserDetailsService {

    private final UserRepositorio userRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositorio.findBylogin(username);
    }
}
