package com.stayoff.agendamento.dto;

import java.util.List;

public record EmpresaPagedDTO(
        List<EmpresaResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
