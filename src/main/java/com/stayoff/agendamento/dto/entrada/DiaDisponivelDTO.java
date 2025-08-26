package com.stayoff.agendamento.dto.entrada;

import java.time.LocalDate;

public record DiaDisponivelDTO(
        Integer profissionalId,
        LocalDate dia
) {}
