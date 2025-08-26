package com.stayoff.agendamento.dto.resposta;

import java.time.LocalDate;

public record DiaDisponivelResponseDTO(
        Integer id,
        Integer profissionalId,
        LocalDate dia
) {}
