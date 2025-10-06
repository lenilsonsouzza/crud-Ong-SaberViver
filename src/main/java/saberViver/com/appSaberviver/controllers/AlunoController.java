package saberViver.com.appSaberviver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.dto.AtividadeDTO;
import saberViver.com.appSaberviver.servicos.AlunoServico;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {


    @Autowired
    private AlunoServico servico;

    @GetMapping(value = "/{id}")
    public AlunoDTO findById(@PathVariable Long id) {
        return servico.findById(id);
    }

    @GetMapping
    public Page<AlunoDTO> findAll(Pageable pageable) {
        return servico.findALL(pageable);

    }
}