package com.stayoff.agendamento.repository;

import com.stayoff.agendamento.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
