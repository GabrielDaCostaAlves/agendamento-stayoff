package com.stayoff.agendamento.dto.resposta;

import java.time.LocalDateTime;

public record BlocoDisponibilidadeResponseDTO(
        Integer id,
        Integer diaDisponivelId,
        LocalDateTime inicio,
        LocalDateTime fim
) {}
