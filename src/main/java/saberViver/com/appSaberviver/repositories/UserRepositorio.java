package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import saberViver.com.appSaberviver.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

   UserDetails findBylogin(String login);
}
