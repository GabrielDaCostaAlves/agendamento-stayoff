package com.stayoff.agendamento.dto.resposta;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        String email,
        String telefone
) {}
