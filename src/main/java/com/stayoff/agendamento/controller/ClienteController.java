package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.ClienteDTO;
import com.stayoff.agendamento.dto.resposta.ClienteResponseDTO;
import com.stayoff.agendamento.dto.paged.ClientePagedDTO;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.service.ClienteService;
import com.stayoff.agendamento.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final EmpresaService empresaService; // 1️⃣ adicionar aqui


    public ClienteController(ClienteService clienteService, EmpresaService empresaService) {
        this.clienteService = clienteService;
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<ClientePagedDTO> getAllClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(clienteService.findAllPaged(page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> createCliente(@RequestBody ClienteDTO dto,
                                                            @RequestParam Long empresaId) {

        Empresa empresa = empresaService.findEmpresaById(empresaId);
        ClienteResponseDTO created = clienteService.save(dto, empresa);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Integer id,
                                                            @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
