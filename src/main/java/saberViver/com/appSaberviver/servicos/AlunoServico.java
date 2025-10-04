package saberViver.com.appSaberviver.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.repositories.AlunoRepository;

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
    public AlunoDTO findByIldAl(Long id) {
        Optional<Aluno> result = repository.findById(id);
        Aluno aluno = result.get();
        AlunoDTO dto = new AlunoDTO();
        dto.setNome(aluno.getNome());
        dto.setCpf(aluno.getCpf());
        dto.setNomeResponsavel(aluno.getNomeResponsavel());
        return dto;

    }
}
