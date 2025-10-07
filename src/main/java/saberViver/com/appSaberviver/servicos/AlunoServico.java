package saberViver.com.appSaberviver.servicos;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.repositories.AlunoRepositorio;
import saberViver.com.appSaberviver.repositories.AtividadeRepositorio;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoServico {


    private final AlunoRepositorio alunoRepository;
    private final AtividadeRepositorio atividadeRepository;

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id).get();
        return new AlunoDTO(aluno);

    }

    @Transactional(readOnly = true)
    public Page<AlunoDTO> findALL(Pageable pageable) {

        Page<Aluno> result = alunoRepository.findAll(pageable);
        return result.map(x -> new AlunoDTO(x));

    }

    @Transactional
    public AlunoDTO inserir(AlunoDTO dto) {
        Aluno entidade = new Aluno();
        entidade.setNome(dto.getNome());
        entidade.setCpf(dto.getCpf());
        entidade.setApelido(dto.getApelido());
        entidade.setDataNascimento(dto.getDataNascimento());
        entidade.setNomeResponsavel(dto.getNomeResponsavel());
        entidade.setCpfResponsavel(dto.getCpfResponsavel());
        entidade.setTelefonePrincipal(dto.getTelefonePrincipal());

        if (dto.getAtividade() != null && !dto.getAtividade().isEmpty()) {
            List<Atividade> atividades = atividadeRepository.findAllById(dto.getAtividade());
            entidade.getAtividades().addAll(atividades);
        }

        entidade = alunoRepository.save(entidade);
        return new AlunoDTO(entidade);


    }

    public List<AlunoDTO> listarTodos() {
        return alunoRepository.findAll().stream().map(AlunoDTO::new).toList();
    }
}