package com.stayoff.agendamento.dto.entrada;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoDTO(

        @NotNull(message = "Cliente é obrigatório")
        Integer clienteId,

        @NotNull(message = "Profissional é obrigatório")
        Integer profissionalId,

        @NotNull(message = "Data de início é obrigatória")
        LocalDateTime inicio,

        @NotNull(message = "Data de fim é obrigatória")
        LocalDateTime fim,

        @Size(max = 255)
        String motivoCancelamento,

        @NotNull(message = "Valor total é obrigatório")
        Double valorTotal,

        List<Integer> servicoIds
) {}
