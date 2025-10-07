package saberViver.com.appSaberviver.servicos;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.dto.ProfessorDTO;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Professor;
import saberViver.com.appSaberviver.repositories.AtividadeRepositorio;
import saberViver.com.appSaberviver.repositories.ProfessorRepositorio;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServico {


    private final ProfessorRepositorio professorRepositorio;
    private final AtividadeRepositorio atividadeRepositorio;

    @Transactional(readOnly = true)
public ProfessorDTO findById(Long id){
    Professor professor = professorRepositorio.findById(id).get();
    return  new ProfessorDTO(professor);
}
    @Transactional(readOnly = true)
    public Page<ProfessorDTO> findALL(Pageable pageable) {

        Page<Professor> result = professorRepositorio.findAll(pageable);
        return result.map(x -> new ProfessorDTO(x));
    }
    @Transactional
    public ProfessorDTO inserir(ProfessorDTO dto) {
        Professor entidade = new Professor();
        entidade.setNome(dto.getNome());
        entidade.setCpf(dto.getCpf());
        entidade.setEmail(dto.getEmail());
        entidade.setDataNascimento(dto.getDataNascimento());
        entidade.setAreaAtuacao(dto.getAreaAtuacao());
        entidade.setSenha(dto.getSenha());
        entidade.setTelefone(dto.getTelefonePricipal());


        if (dto.getAtividade() != null && !dto.getAtividade().isEmpty()) {
            List<Atividade> atividades = atividadeRepositorio.findAllById(dto.getAtividade());
            entidade.getAtividades().addAll(atividades);
        }

        entidade = professorRepositorio.save(entidade);
        return new ProfessorDTO(entidade);


    }

    public List<ProfessorDTO> listarTodos() {
        return professorRepositorio.findAll().stream().map(ProfessorDTO::new).toList();
    }




}