package com.stayoff.agendamento.dto;

import java.time.LocalDateTime;

public record BlocoDisponibilidadeResponseDTO(
        Integer id,
        Integer diaDisponivelId,
        LocalDateTime inicio,
        LocalDateTime fim
) {}
