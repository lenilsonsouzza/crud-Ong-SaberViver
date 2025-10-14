package saberViver.com.appSaberviver.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.AdministradorDTO;
import saberViver.com.appSaberviver.dto.AlunoDTO;
import saberViver.com.appSaberviver.dto.CadastroAdministradorDTO;
import saberViver.com.appSaberviver.servicos.AdministradorServico;

import java.net.URI;


@RestController
@RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorController {


    private final AdministradorServico administradorServico;

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<AdministradorDTO> findyByCpf(@PathVariable String cpf) {
        AdministradorDTO dto = administradorServico.findyByCpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<AdministradorDTO> findyByNome(@PathVariable String nome) {
        AdministradorDTO dto = administradorServico.findyByNome(nome);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public ResponseEntity<Page<AdministradorDTO>> findAll(Pageable page) {
        Page<AdministradorDTO> dto = administradorServico.findALL(page);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AdministradorDTO> cadastrar(@RequestBody @Valid CadastroAdministradorDTO dto) {
        AdministradorDTO criado = administradorServico.cadastrarAdministrador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<AdministradorDTO> atualizar(@PathVariable long id, @RequestBody AdministradorDTO dto) {
        dto = administradorServico.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        administradorServico.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
