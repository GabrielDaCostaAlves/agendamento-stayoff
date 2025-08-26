package com.stayoff.agendamento.dto.resposta;

import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponseDTO(

        Integer id,
        Integer clienteId,
        Integer profissionalId,
        LocalDateTime inicio,
        LocalDateTime fim,
        String status,
        String motivoCancelamento,
        Double valorTotal,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        LocalDateTime dataCancelamento,
        LocalDateTime dataConfirmacao,
        List<Integer> servicoIds
) {}
