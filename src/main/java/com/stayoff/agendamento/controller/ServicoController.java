package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.ServicoDTO;
import com.stayoff.agendamento.dto.paged.ServicoPagedDTO;
import com.stayoff.agendamento.dto.resposta.ServicoResponseDTO;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<ServicoPagedDTO> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        ServicoPagedDTO result = servicoService.findAllPaged(page, size, sortBy, direction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> getById(@PathVariable Integer id) {
        ServicoResponseDTO servico = servicoService.findById(id);
        return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> create(@RequestBody ServicoDTO dto, @RequestParam Long empresaId) {

        Empresa empresa = new Empresa();
        empresa.setId(empresaId);

        ServicoResponseDTO created = servicoService.save(dto, empresa);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> update(@PathVariable Integer id, @RequestBody ServicoDTO dto) {
        ServicoResponseDTO updated = servicoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
