package com.stayoff.agendamento.repository;

import com.stayoff.agendamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
