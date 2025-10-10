package saberViver.com.appSaberviver.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.VoluntarioDTO;
import saberViver.com.appSaberviver.servicos.VoluntarioServico;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/voluntarios")
public class VoluntarioController {


    private final VoluntarioServico professorServico;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VoluntarioDTO> findById(@PathVariable Long id) {
        VoluntarioDTO dto = professorServico.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<VoluntarioDTO>> findAll(Pageable pageable) {
        Page<VoluntarioDTO> dto = professorServico.findALL(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VoluntarioDTO> inserir(@Valid  @RequestBody VoluntarioDTO dto) {
        dto = professorServico.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VoluntarioDTO> atualizar(@PathVariable long id, @RequestBody VoluntarioDTO dto) {
        dto = professorServico.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        professorServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
