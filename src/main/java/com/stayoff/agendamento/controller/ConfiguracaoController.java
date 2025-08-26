package com.stayoff.agendamento.controller;

import com.stayoff.agendamento.dto.entrada.ConfiguracaoDTO;
import com.stayoff.agendamento.dto.resposta.ConfiguracaoResponseDTO;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.service.ConfiguracaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuracoes")
public class ConfiguracaoController {

    private final ConfiguracaoService configuracaoService;

    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }


    @GetMapping
    public ResponseEntity<ConfiguracaoResponseDTO> getConfiguracao(@RequestAttribute Empresa empresa) {
        ConfiguracaoResponseDTO configuracao = configuracaoService.findByEmpresa(empresa);
        return ResponseEntity.ok(configuracao);
    }


    @PostMapping
    public ResponseEntity<ConfiguracaoResponseDTO> saveOrUpdateConfiguracao(@RequestBody ConfiguracaoDTO dto,
                                                                            @RequestAttribute Empresa empresa) {
        ConfiguracaoResponseDTO saved = configuracaoService.saveOrUpdate(dto, empresa);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
