package com.stayoff.agendamento.repository;

import com.stayoff.agendamento.model.Configuracao;
import com.stayoff.agendamento.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Integer> {

    Optional<Configuracao> findByEmpresa(Empresa empresa);

}
