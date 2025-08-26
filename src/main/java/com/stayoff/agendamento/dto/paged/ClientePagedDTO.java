package com.stayoff.agendamento.dto.paged;

import com.stayoff.agendamento.dto.resposta.ClienteResponseDTO;

import java.util.List;

public record ClientePagedDTO(
        List<ClienteResponseDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
