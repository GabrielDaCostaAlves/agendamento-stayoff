package com.stayoff.agendamento.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 255)
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Size(max = 255)
        String email,

        @Size(max = 50)
        String telefone,

        @Size(max = 500)
        String endereco,

        @Size(max = 100)
        String subdominio


) {}
