package com.stayoff.agendamento.dto;

import jakarta.validation.constraints.NotBlank;

public record ConfiguracaoDTO(

        @NotBlank(message = "ConfigJson é obrigatório")
        String configJson
) {}
