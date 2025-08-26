package com.stayoff.agendamento.dto;

import java.time.LocalDate;

public record DiaDisponivelResponseDTO(
        Integer id,
        Integer profissionalId,
        LocalDate dia
) {}
