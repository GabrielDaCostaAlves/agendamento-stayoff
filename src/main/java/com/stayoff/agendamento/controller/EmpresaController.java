package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.EmpresaDTO;
import com.stayoff.agendamento.dto.paged.EmpresaPagedDTO;
import com.stayoff.agendamento.dto.resposta.EmpresaResponseDTO;
import com.stayoff.agendamento.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<EmpresaPagedDTO> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        EmpresaPagedDTO result = empresaService.findAllPaged(page, size, sortBy, direction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> getById(@PathVariable Long id) {
        EmpresaResponseDTO empresa = empresaService.findById(id);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> create(@RequestBody EmpresaDTO dto) {
        EmpresaResponseDTO created = empresaService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> update(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
        EmpresaResponseDTO updated = empresaService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
