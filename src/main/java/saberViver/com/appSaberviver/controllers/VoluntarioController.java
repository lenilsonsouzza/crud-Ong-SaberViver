package saberViver.com.appSaberviver.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saberViver.com.appSaberviver.dto.AdministradorDTO;
import saberViver.com.appSaberviver.dto.CadastroAdministradorDTO;
import saberViver.com.appSaberviver.dto.CadastroVoluntarioDTO;
import saberViver.com.appSaberviver.dto.VoluntarioDTO;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.entidades.Voluntario;
import saberViver.com.appSaberviver.servicos.VoluntarioServico;

import java.util.List;
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/voluntarios")
public class VoluntarioController {


    private final VoluntarioServico voluntarioServico;

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<VoluntarioDTO> findByCpf(@PathVariable String cpf) {
        VoluntarioDTO dto = voluntarioServico.findByCpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<Voluntario>> buscarPorNome(@PathVariable String nome) {
        List<Voluntario> voluntario = voluntarioServico.buscarPorNome(nome);
        if (voluntario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voluntario);
    }

    @GetMapping
    public ResponseEntity<Page<VoluntarioDTO>> findAll(Pageable pageable) {
        Page<VoluntarioDTO> dto = voluntarioServico.findALL(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VoluntarioDTO> cadastrar(@RequestBody @Valid CadastroVoluntarioDTO dto) {
        VoluntarioDTO criado = voluntarioServico.cadastrarVoluntario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VoluntarioDTO> atualizar(@PathVariable long id, @RequestBody VoluntarioDTO dto) {
        dto = voluntarioServico.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        voluntarioServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
