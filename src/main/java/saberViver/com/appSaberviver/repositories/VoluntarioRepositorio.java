package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Voluntario;

import java.util.Optional;

public interface VoluntarioRepositorio extends JpaRepository<Voluntario, Long> {

    Optional<Voluntario> findByCpf(String cpf);

    Optional<Voluntario> findByNome(String nome);

}
