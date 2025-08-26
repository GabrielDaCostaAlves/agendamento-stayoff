package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.ConfiguracaoResponseDTO;

import java.util.List;

public record ConfiguracaoPagedDTO(
        List<ConfiguracaoResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
