package saberViver.com.appSaberviver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.servicos.AlunoServico;

import java.net.URI;

@RestController
@RequestMapping(value = "/alunos")
@RequiredArgsConstructor
public class AlunoController {



    private final AlunoServico alunoServico;


    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) {
       AlunoDTO dto = alunoServico.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAll(Pageable pageable) {
      Page<AlunoDTO> dto = alunoServico.findALL(pageable);
               return  ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> inserir(@RequestBody AlunoDTO dto) {
        dto = alunoServico.inserir(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable long id, @RequestBody AlunoDTO dto) {
        dto = alunoServico.atualizar(id,dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
