package saberViver.com.appSaberviver.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.dto.ProfessorDTO;
import saberViver.com.appSaberviver.servicos.ProfessorServico;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {


    private final ProfessorServico professorServico;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable Long id) {
        ProfessorDTO dto = professorServico.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProfessorDTO>> findAll(Pageable pageable) {
        Page<ProfessorDTO> dto = professorServico.findALL(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> inserir(@RequestBody ProfessorDTO dto) {
        dto = professorServico.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfessorDTO> atualizar(@PathVariable long id, @RequestBody ProfessorDTO dto) {
        dto = professorServico.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        professorServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
