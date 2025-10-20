package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.entidades.Voluntario;
import saberViver.com.appSaberviver.entidades.user.User;

import java.util.List;
import java.util.Optional;

public interface AdministradorRepositorio extends JpaRepository<Administrador,Long> {

    Optional<Administrador> findByCpf(String cpf);

    List<Administrador> findByNomeContainingIgnoreCase(String nome);
    Administrador findByUser(User user);

}
