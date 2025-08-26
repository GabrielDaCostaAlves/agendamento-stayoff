package com.stayoff.agendamento.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bloco_disponibilidade")
public class BlocoDisponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "dia_disponivel_id", nullable = false)
    private DiaDisponivel diaDisponivel;

    private java.time.LocalDateTime inicio;
    private java.time.LocalDateTime fim;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public DiaDisponivel getDiaDisponivel() {
        return diaDisponivel;
    }

    public void setDiaDisponivel(DiaDisponivel diaDisponivel) {
        this.diaDisponivel = diaDisponivel;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
}
