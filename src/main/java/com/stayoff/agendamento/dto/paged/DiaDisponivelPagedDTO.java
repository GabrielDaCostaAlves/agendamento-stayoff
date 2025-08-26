package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.DiaDisponivelResponseDTO;

import java.util.List;

public record DiaDisponivelPagedDTO(
        List<DiaDisponivelResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
