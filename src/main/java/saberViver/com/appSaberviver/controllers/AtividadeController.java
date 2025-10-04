package saberViver.com.appSaberviver.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saberViver.com.appSaberviver.dto.AtividadeDTO;

import saberViver.com.appSaberviver.servicos.AtividadeServico;



@RestController
@RequestMapping(value = "/atividades")
public class AtividadeController {


    @Autowired
    private AtividadeServico servico;

    @GetMapping(value = "/{id}")
    public AtividadeDTO findById(@PathVariable Long id) {

        return servico.findById(id);

    }

    @GetMapping
    public Page<AtividadeDTO> findAll(Pageable pageable) {
        return servico.findALL(pageable);

    }
}
