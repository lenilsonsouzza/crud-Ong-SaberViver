package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import saberViver.com.appSaberviver.entidades.user.User;

public interface UserRepositorio extends JpaRepository<User,Long> {

   UserDetails findBylogin(String login);
}
