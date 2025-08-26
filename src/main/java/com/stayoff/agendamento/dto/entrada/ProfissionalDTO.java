package com.stayoff.agendamento.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ProfissionalDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 255)
        String nome,

        List<Integer> servicoIds
) {}
