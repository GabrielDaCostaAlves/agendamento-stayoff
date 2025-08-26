package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.DiaDisponivelDTO;
import com.stayoff.agendamento.dto.entrada.BlocoDisponibilidadeDTO;
import com.stayoff.agendamento.dto.resposta.DiaDisponivelResponseDTO;
import com.stayoff.agendamento.dto.resposta.BlocoDisponibilidadeResponseDTO;
import com.stayoff.agendamento.dto.paged.BlocoDisponibilidadePagedDTO;
import com.stayoff.agendamento.service.BlocoDisponibilidadeService;
import com.stayoff.agendamento.service.DiaDisponivelService;
import com.stayoff.agendamento.model.Empresa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidade")
public class DisponibilidadeController {

    private final DiaDisponivelService diaDisponivelService;
    private final BlocoDisponibilidadeService blocoService;

    public DisponibilidadeController(DiaDisponivelService diaDisponivelService,
                                     BlocoDisponibilidadeService blocoService) {
        this.diaDisponivelService = diaDisponivelService;
        this.blocoService = blocoService;
    }

    // --------- DIAS DISPONÍVEIS ---------
    @GetMapping("/dias/profissional/{profissionalId}")
    public ResponseEntity<List<DiaDisponivelResponseDTO>> getDiasByProfissional(@PathVariable Integer profissionalId) {
        List<DiaDisponivelResponseDTO> dias = diaDisponivelService.findAllByProfissional(profissionalId);
        return ResponseEntity.ok(dias);
    }

    @GetMapping("/dias/{id}")
    public ResponseEntity<DiaDisponivelResponseDTO> getDiaById(@PathVariable Integer id) {
        return ResponseEntity.ok(diaDisponivelService.findById(id));
    }

    @PostMapping("/dias")
    public ResponseEntity<DiaDisponivelResponseDTO> createDia(@RequestBody DiaDisponivelDTO dto, @RequestAttribute Empresa empresa) {
        DiaDisponivelResponseDTO created = diaDisponivelService.save(dto, empresa);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/dias/{id}")
    public ResponseEntity<DiaDisponivelResponseDTO> updateDia(@PathVariable Integer id, @RequestBody DiaDisponivelDTO dto) {
        return ResponseEntity.ok(diaDisponivelService.update(id, dto));
    }

    @DeleteMapping("/dias/{id}")
    public ResponseEntity<Void> deleteDia(@PathVariable Integer id) {
        diaDisponivelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------- BLOCOS DE DISPONIBILIDADE ---------
    @GetMapping("/blocos")
    public ResponseEntity<BlocoDisponibilidadePagedDTO> getBlocosPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(blocoService.findAllPaged(page, size, sortBy, direction));
    }

    @GetMapping("/blocos/{id}")
    public ResponseEntity<BlocoDisponibilidadeResponseDTO> getBlocoById(@PathVariable Integer id) {
        return ResponseEntity.ok(blocoService.findById(id));
    }

    @PostMapping("/blocos")
    public ResponseEntity<BlocoDisponibilidadeResponseDTO> createBloco(@RequestBody BlocoDisponibilidadeDTO dto) {
        BlocoDisponibilidadeResponseDTO created = blocoService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/blocos/{id}")
    public ResponseEntity<BlocoDisponibilidadeResponseDTO> updateBloco(@PathVariable Integer id, @RequestBody BlocoDisponibilidadeDTO dto) {
        return ResponseEntity.ok(blocoService.update(id, dto));
    }

    @DeleteMapping("/blocos/{id}")
    public ResponseEntity<Void> deleteBloco(@PathVariable Integer id) {
        blocoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
