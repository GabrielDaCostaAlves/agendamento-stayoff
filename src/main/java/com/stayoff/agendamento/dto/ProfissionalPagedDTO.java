package com.stayoff.agendamento.dto;

import java.util.List;

public record ProfissionalPagedDTO(
        List<ProfissionalResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
