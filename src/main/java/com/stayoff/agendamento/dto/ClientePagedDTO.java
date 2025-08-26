package com.stayoff.agendamento.dto;

import java.util.List;

public record ClientePagedDTO(
        List<ClienteResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
