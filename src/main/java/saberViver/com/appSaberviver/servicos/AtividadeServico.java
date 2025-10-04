package saberViver.com.appSaberviver.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.repositories.AtividadeRepository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeServico {

    @Autowired
    private AtividadeRepository repository;

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
}