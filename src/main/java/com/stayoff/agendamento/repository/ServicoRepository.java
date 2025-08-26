package com.stayoff.agendamento.repository;

import com.stayoff.agendamento.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
