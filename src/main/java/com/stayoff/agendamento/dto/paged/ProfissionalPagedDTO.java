package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.ProfissionalResponseDTO;

import java.util.List;

public record ProfissionalPagedDTO(
        List<ProfissionalResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
