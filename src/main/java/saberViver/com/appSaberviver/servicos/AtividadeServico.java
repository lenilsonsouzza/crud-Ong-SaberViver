package saberViver.com.appSaberviver.servicos;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.repositories.AtividadeRepositorio;
import org.springframework.data.domain.Page;
import saberViver.com.appSaberviver.servicos.exceptions.ResourceNotFoundException;

@Service
public class AtividadeServico {

    @Autowired
    private AtividadeRepositorio atividadeRepositorio;

    @Transactional(readOnly = true)
    public AtividadeDTO findById(Long id) {

        Atividade atividade = atividadeRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada"));
        return new AtividadeDTO(atividade);
    }

    @Transactional(readOnly = true)
    public Page<AtividadeDTO> findALL(Pageable pageable) {

        Page<Atividade> result = atividadeRepositorio.findAll(pageable);
        return result.map(x -> new AtividadeDTO(x));
    }

    @Transactional
    public AtividadeDTO inserir(AtividadeDTO dto) {

        Atividade entidade = new Atividade();

        copiarDtoPentidade(dto, entidade);
        entidade = atividadeRepositorio.save(entidade);

        return new AtividadeDTO(entidade);
    }

    @Transactional
    public AtividadeDTO atualizar(long id, AtividadeDTO dto) {
try {
    Atividade entidade = atividadeRepositorio.getReferenceById(id);

    copiarDtoPentidade(dto, entidade);

    entidade = atividadeRepositorio.save(entidade);

    return new AtividadeDTO(entidade);
}catch (EntityNotFoundException e){
    throw  new ResourceNotFoundException(" Atividade não encontrada");
}
    }

    @Transactional
    public void delete(Long id) {

            Atividade atividade = atividadeRepositorio.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada"));

    }
    private void copiarDtoPentidade(AtividadeDTO dto, Atividade entidade) {
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
    }


}