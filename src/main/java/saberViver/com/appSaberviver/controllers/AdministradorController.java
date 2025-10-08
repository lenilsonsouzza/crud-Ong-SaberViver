package saberViver.com.appSaberviver.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saberViver.com.appSaberviver.dto.AdministradorDTO;
import saberViver.com.appSaberviver.servicos.AdministradorServico;

import java.net.URI;


@RestController
@RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorController {


     private final AdministradorServico administradorServico;

@GetMapping(value = "/{id}")
    public ResponseEntity<AdministradorDTO> findyById(@PathVariable long id) {
        AdministradorDTO dto = administradorServico.findyById(id);
        return ResponseEntity.ok(dto);

    }
          @GetMapping
        public ResponseEntity<Page<AdministradorDTO>> findAll(Pageable page) {
            Page<AdministradorDTO> dto =administradorServico.findALL(page);
            return  ResponseEntity.ok(dto);
        }
    @PostMapping
    public ResponseEntity<AdministradorDTO> inserir(@RequestBody AdministradorDTO dto) {
        dto = administradorServico.inserir(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
