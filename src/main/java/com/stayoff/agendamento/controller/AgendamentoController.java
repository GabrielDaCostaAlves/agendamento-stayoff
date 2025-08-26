package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.AgendamentoDTO;
import com.stayoff.agendamento.dto.resposta.AgendamentoResponseDTO;
import com.stayoff.agendamento.dto.paged.AgendamentoPagedDTO;
import com.stayoff.agendamento.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    //Todo: criar a parte  de

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<AgendamentoPagedDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        AgendamentoPagedDTO result = agendamentoService.findAllPaged(page, size, sortBy, direction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> getById(@PathVariable Integer id) {
        AgendamentoResponseDTO agendamento = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> create(@RequestBody AgendamentoDTO dto) {
        AgendamentoResponseDTO created = agendamentoService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody AgendamentoDTO dto
    ) {
        AgendamentoResponseDTO updated = agendamentoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
