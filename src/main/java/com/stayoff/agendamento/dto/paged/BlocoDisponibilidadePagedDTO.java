package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.BlocoDisponibilidadeResponseDTO;

import java.util.List;

public record BlocoDisponibilidadePagedDTO(
        List<BlocoDisponibilidadeResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
