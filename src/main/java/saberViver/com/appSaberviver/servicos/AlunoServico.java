package saberViver.com.appSaberviver.servicos;

import jakarta.persistence.EntityNotFoundException;
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
import saberViver.com.appSaberviver.servicos.exceptions.ResourceNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoServico {


    private final AlunoRepositorio alunoRepositorio;
    private final AtividadeRepositorio atividadeRepositorio;

    @Transactional(readOnly = true)
    public AlunoDTO findBycpf(String cpf) {
        Aluno aluno = alunoRepositorio.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AlunoDTO(aluno);


    }
    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    /*
    @Transactional(readOnly = true)
    public AlunoDTO findByNome(String nome) {
        Aluno aluno = alunoRepositorio.findByNome(nome).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AlunoDTO(aluno);

    }*/

    @Transactional(readOnly = true)
    public Page<AlunoDTO> findALL(Pageable page) {

        Page<Aluno> result = alunoRepositorio.findAll(page);
        return result.map(x -> new AlunoDTO(x));

    }

    @Transactional
    public AlunoDTO inserir(AlunoDTO dto) {
        Aluno entidade = new Aluno();

        copiarAlunoDTOparaEntidade(dto, entidade);

        entidade = alunoRepositorio.save(entidade);

        return new AlunoDTO(entidade);


    }

    @Transactional
    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        try {


            Aluno entidade = alunoRepositorio.getReferenceById(id);

            copiarAlunoDTOparaEntidade(dto, entidade);

            entidade = alunoRepositorio.save(entidade);
            return new AlunoDTO(entidade);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(" recurso não encontrado");
        }

    }

    public void deletar(Long id) {

        Aluno aluno = alunoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrada"));

        alunoRepositorio.delete(aluno);
    }

    private void copiarAlunoDTOparaEntidade(AlunoDTO dto, Aluno entidade) {

        entidade.setNome(dto.getNome());
        entidade.setSobreNome(dto.getSobreNome());
        entidade.setCpf(dto.getCpf());
        entidade.setApelido(dto.getApelido());
        entidade.setDataNascimento(dto.getDataNascimento());
        entidade.setNomeResponsavel(dto.getNomeResponsavel());
        entidade.setCpfResponsavel(dto.getCpfResponsavel());
        entidade.setTelefonePrincipal(dto.getTelefonePrincipal());
        entidade.setTermoAutorizado((dto.isTermoAutorizado()));


        if (dto.getAtividade() != null && !dto.getAtividade().isEmpty()) {
            List<Atividade> atividades = atividadeRepositorio.findAllById(dto.getAtividade());
            entidade.getAtividades().addAll(atividades);
        }

    }

}