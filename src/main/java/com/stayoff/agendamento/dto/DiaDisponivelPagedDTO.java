package com.stayoff.agendamento.dto;

import java.util.List;

public record DiaDisponivelPagedDTO(
        List<DiaDisponivelResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
