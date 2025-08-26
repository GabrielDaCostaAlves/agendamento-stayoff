package com.stayoff.agendamento.dto;

import java.util.List;

public record AgendamentoPagedDTO(
        List<AgendamentoResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
