package com.stayoff.agendamento.dto.entrada;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record BlocoDisponibilidadeDTO(


        @NotNull(message = "DiaDisponivelId é obrigatório")
        Integer diaDisponivelId,

        @NotNull(message = "Data de início é obrigatória")
        LocalDateTime inicio,

        @NotNull(message = "Data de fim é obrigatória")
        LocalDateTime fim
) {}
