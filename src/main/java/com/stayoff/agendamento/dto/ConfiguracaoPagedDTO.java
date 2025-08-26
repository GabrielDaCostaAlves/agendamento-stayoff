package com.stayoff.agendamento.dto;

import java.util.List;

public record ConfiguracaoPagedDTO(
        List<ConfiguracaoResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
