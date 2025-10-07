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
    private AtividadeRepositorio repository;

    @Transactional(readOnly = true)
    public AtividadeDTO findById(Long id) {

        //Optional<Atividade> result  = repository.findById(id);
        Atividade atividade = repository.findById(id).get();
        return new AtividadeDTO(atividade);
        //AtividadeDTO dto = new AtividadeDTO(atividade);
        // return  dto;

    }

    @Transactional(readOnly = true)
    public Page<AtividadeDTO> findALL(Pageable pageable) {

        Page<Atividade> result = repository.findAll(pageable);
        return result.map(x -> new AtividadeDTO(x));

    }

    @Transactional
    public AtividadeDTO inserir(AtividadeDTO dto) {
        Atividade entidade = new Atividade();
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());

        entidade= repository.save(entidade);

        return new AtividadeDTO(entidade);
    }

}