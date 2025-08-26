package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.ProfissionalDTO;
import com.stayoff.agendamento.dto.paged.ProfissionalPagedDTO;
import com.stayoff.agendamento.dto.resposta.ProfissionalResponseDTO;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.service.ProfissionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<ProfissionalPagedDTO> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        ProfissionalPagedDTO result = profissionalService.findAllPaged(page, size, sortBy, direction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDTO> getById(@PathVariable Integer id) {
        ProfissionalResponseDTO profissional = profissionalService.findById(id);
        return ResponseEntity.ok(profissional);
    }

    @PostMapping
    public ResponseEntity<ProfissionalResponseDTO> create(@RequestBody ProfissionalDTO dto, @RequestParam Long empresaId) {

        Empresa empresa = new Empresa();
        empresa.setId(empresaId);

        ProfissionalResponseDTO created = profissionalService.save(dto, empresa);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalResponseDTO> update(@PathVariable Integer id, @RequestBody ProfissionalDTO dto) {
        ProfissionalResponseDTO updated = profissionalService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
