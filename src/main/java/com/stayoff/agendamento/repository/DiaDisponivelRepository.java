package com.stayoff.agendamento.repository;

import com.stayoff.agendamento.model.DiaDisponivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiaDisponivelRepository extends JpaRepository<DiaDisponivel, Integer> {

    List<DiaDisponivel> findByProfissionalId(Integer profissionalId);
}

