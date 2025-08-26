package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.ServicoResponseDTO;

import java.util.List;

public record ServicoPagedDTO(
        List<ServicoResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
