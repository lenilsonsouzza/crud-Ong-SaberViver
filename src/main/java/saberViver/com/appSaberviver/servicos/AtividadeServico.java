package saberViver.com.appSaberviver.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.repositories.AtividadeRepositorio;
import org.springframework.data.domain.Page;

@Service
public class AtividadeServico {

    @Autowired
    private AtividadeRepositorio atividadeRepositorio;

    @Transactional(readOnly = true)
    public AtividadeDTO findById(Long id) {

        Atividade atividade = atividadeRepositorio.findById(id).get();
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
        Atividade entidade = atividadeRepositorio.getReferenceById(id);

        copiarDtoPentidade(dto, entidade);

        entidade = atividadeRepositorio.save(entidade);

        return new AtividadeDTO(entidade);
    }

    @Transactional
    public void delete(Long id) {
       atividadeRepositorio.deleteById(id);
    }

    private void copiarDtoPentidade(AtividadeDTO dto, Atividade entidade) {
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
    }


}