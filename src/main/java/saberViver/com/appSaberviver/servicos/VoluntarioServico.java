package saberViver.com.appSaberviver.servicos;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.VoluntarioDTO;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.entidades.Voluntario;
import saberViver.com.appSaberviver.repositories.AtividadeRepositorio;
import saberViver.com.appSaberviver.repositories.VoluntarioRepositorio;
import saberViver.com.appSaberviver.servicos.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntarioServico {


    private final VoluntarioRepositorio voluntarioRepositorio;
    private final AtividadeRepositorio atividadeRepositorio;

    @Transactional(readOnly = true)
    public VoluntarioDTO findById(Long id) {
        Voluntario professor = voluntarioRepositorio.findById(id).orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
        return new VoluntarioDTO(professor);
    }

    @Transactional(readOnly = true)
    public Page<VoluntarioDTO> findALL(Pageable pageable) {

        Page<Voluntario> result = voluntarioRepositorio.findAll(pageable);
        return result.map(x -> new VoluntarioDTO(x));
    }

    @Transactional
    public VoluntarioDTO inserir(VoluntarioDTO dto) {
        Voluntario entidade = new Voluntario();
        copiarDtoparaEntidade(dto, entidade);

        entidade = voluntarioRepositorio.save(entidade);
        return new VoluntarioDTO(entidade);

    }
    @Transactional
    public VoluntarioDTO atualizar(long id, VoluntarioDTO dto) {
        try {


            Voluntario entidade = voluntarioRepositorio.getReferenceById(id);

            copiarDtoparaEntidade(dto, entidade);

            entidade = voluntarioRepositorio.save(entidade);
            return new VoluntarioDTO(entidade);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Voluntario não encontrado");
        }
    }

    public void deletar(Long id) {
        Voluntario professor = voluntarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Voluntario não encontrado"));

       voluntarioRepositorio.delete(professor);
    }

    private void copiarDtoparaEntidade(VoluntarioDTO dto, Voluntario entidade) {
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

    }


}