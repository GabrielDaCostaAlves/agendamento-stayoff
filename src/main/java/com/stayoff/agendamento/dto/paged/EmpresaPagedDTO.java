package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.EmpresaResponseDTO;

import java.util.List;

public record EmpresaPagedDTO(
        List<EmpresaResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
