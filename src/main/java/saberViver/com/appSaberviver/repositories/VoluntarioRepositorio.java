package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Voluntario;
import saberViver.com.appSaberviver.entidades.user.User;

import java.util.List;
import java.util.Optional;

public interface VoluntarioRepositorio extends JpaRepository<Voluntario, Long> {

    Optional<Voluntario> findByCpf(String cpf);

    List<Voluntario> findByNomeContainingIgnoreCase(String nome);
    Voluntario findByUser(User user);
}
