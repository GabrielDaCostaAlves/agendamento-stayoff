package com.stayoff.agendamento.dto.entrada;

import jakarta.validation.constraints.NotBlank;

public record ConfiguracaoDTO(

        @NotBlank(message = "ConfigJson é obrigatório")
        String configJson
) {}
