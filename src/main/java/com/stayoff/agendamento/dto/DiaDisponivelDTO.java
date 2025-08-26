package com.stayoff.agendamento.dto;

import java.time.LocalDate;

public record DiaDisponivelDTO(
        Integer profissionalId,
        LocalDate dia
) {}
