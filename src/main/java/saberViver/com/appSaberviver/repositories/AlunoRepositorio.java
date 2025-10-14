package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saberViver.com.appSaberviver.entidades.Aluno;

import java.util.Optional;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByCpf(String cpf);

    Optional<Aluno> findByNome(String nome);
}
