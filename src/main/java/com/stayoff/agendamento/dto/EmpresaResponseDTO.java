
package com.stayoff.agendamento.dto;

public record EmpresaResponseDTO(
        String nome,
        String email,
        String telefone,
        String endereco,
        String subdominio
) {}
