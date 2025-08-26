package com.stayoff.agendamento.dto;

public record ClienteResponseDTO(
        Integer id,
        Integer empresaId,
        String nome,
        String email,
        String telefone
) {}
