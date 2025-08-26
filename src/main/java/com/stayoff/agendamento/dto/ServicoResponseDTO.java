package com.stayoff.agendamento.dto;

public record ServicoResponseDTO(
        Integer id,
        String nome,
        String descricao,
        Integer duracao,
        Double preco,
        String categoria
) {}
