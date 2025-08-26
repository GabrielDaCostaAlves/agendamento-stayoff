package com.stayoff.agendamento.dto;

import java.util.List;

public record BlocoDisponibilidadePagedDTO(
        List<BlocoDisponibilidadeResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
