package com.stayoff.agendamento.dto;

import java.util.List;

public record ServicoPagedDTO(
        List<ServicoResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
