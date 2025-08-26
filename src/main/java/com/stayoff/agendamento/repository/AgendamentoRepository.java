package com.stayoff.agendamento.repository;

import com.stayoff.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

}
