package saberViver.com.appSaberviver.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.AtividadeDTO;

import saberViver.com.appSaberviver.servicos.AtividadeServico;

import java.net.URI;


@RestController
@RequestMapping(value = "/atividades")
public class AtividadeController {


    @Autowired
    private AtividadeServico atividadeServico;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AtividadeDTO> findById(@PathVariable Long id) {
        AtividadeDTO dto = atividadeServico.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<AtividadeDTO>> findAll(Pageable pageable) {
        Page<AtividadeDTO> dto = atividadeServico.findALL(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AtividadeDTO> inserir(@RequestBody AtividadeDTO dto) {
        dto = atividadeServico.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping (value = "/{id}")
    public ResponseEntity<AtividadeDTO> atualizar(@PathVariable long id, @RequestBody AtividadeDTO dto) {
         dto = atividadeServico.atualizar(id,dto);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
     atividadeServico.delete(id);
        return ResponseEntity.noContent().build();
    }

}
