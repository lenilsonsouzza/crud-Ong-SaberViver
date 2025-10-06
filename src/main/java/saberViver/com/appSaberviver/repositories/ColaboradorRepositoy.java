package saberViver.com.appSaberviver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saberViver.com.appSaberviver.entidades.Professor;

public interface ColaboradorRepositoy extends JpaRepository<Professor, Long> {
}
