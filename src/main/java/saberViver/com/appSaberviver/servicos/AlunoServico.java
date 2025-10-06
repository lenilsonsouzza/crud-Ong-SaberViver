package saberViver.com.appSaberviver.servicos;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.repositories.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServico {


    @Autowired
    private AlunoRepository repository;

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        Aluno aluno = repository.findById(id).get();
        return new AlunoDTO(aluno);

    }

    @Transactional(readOnly = true)
    public Page<AlunoDTO> findALL(Pageable pageable) {

        Page<Aluno> result = repository.findAll(pageable);
        return result.map(x -> new AlunoDTO(x));

    }


}