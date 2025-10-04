package saberViver.com.appSaberviver.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.entidades.Atividade;
import saberViver.com.appSaberviver.repositories.AtividadeRepository;

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


}
