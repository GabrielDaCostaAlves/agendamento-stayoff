
package com.stayoff.agendamento.dto.resposta;

public record EmpresaResponseDTO(
        String nome,
        String email,
        String telefone,
        String endereco,
        String subdominio
) {}
