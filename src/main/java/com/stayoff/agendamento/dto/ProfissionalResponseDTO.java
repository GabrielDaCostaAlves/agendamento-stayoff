package com.stayoff.agendamento.dto;

import java.util.List;

public record ProfissionalResponseDTO(
        Integer id,
        String nome,
        List<String> servicos

) {}
