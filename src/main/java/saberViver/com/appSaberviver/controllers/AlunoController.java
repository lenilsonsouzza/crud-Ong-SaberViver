package saberViver.com.appSaberviver.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.servicos.AlunoServico;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
@RequiredArgsConstructor
public class AlunoController {


    private final AlunoServico alunoServico;


    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<AlunoDTO> findByCpf(@PathVariable String cpf) {
        AlunoDTO dto = alunoServico.findBycpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<Aluno>> buscarPorNome(@RequestParam String nome) {
        List<Aluno> alunos = alunoServico.buscarPorNome(nome);
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }

    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAll(Pageable pageable) {
        Page<AlunoDTO> dto = alunoServico.findALL(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/publico")
    public ResponseEntity<AlunoDTO> inserirPublico(@Valid @RequestBody AlunoDTO dto) {
        dto = alunoServico.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> inserir(@Valid @RequestBody AlunoDTO dto) {
        dto = alunoServico.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable long id, @RequestBody AlunoDTO dto) {
        dto = alunoServico.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
