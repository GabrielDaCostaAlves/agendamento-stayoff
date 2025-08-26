package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.AgendamentoResponseDTO;

import java.util.List;

public record AgendamentoPagedDTO(
        List<AgendamentoResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
