package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.DiaDisponivelDTO;
import com.stayoff.agendamento.dto.DiaDisponivelResponseDTO;
import com.stayoff.agendamento.model.DiaDisponivel;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.model.Profissional;
import com.stayoff.agendamento.repository.DiaDisponivelRepository;
import com.stayoff.agendamento.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaDisponivelService {

    private final DiaDisponivelRepository diaDisponivelRepository;
    private final ProfissionalRepository profissionalRepository;

    public DiaDisponivelService(DiaDisponivelRepository diaDisponivelRepository,
                                ProfissionalRepository profissionalRepository) {
        this.diaDisponivelRepository = diaDisponivelRepository;
        this.profissionalRepository = profissionalRepository;
    }

    // Buscar todos os dias disponíveis
    public List<DiaDisponivelResponseDTO> findAllByProfissional(Integer profissionalId) {
        List<DiaDisponivel> dias = diaDisponivelRepository.findByProfissionalId(profissionalId);
        return dias.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public DiaDisponivelResponseDTO findById(Integer id) {
        DiaDisponivel dia = diaDisponivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiaDisponivel não encontrado"));
        return mapToResponseDTO(dia);
    }

    // Criar
    public DiaDisponivelResponseDTO save(DiaDisponivelDTO dto, Empresa empresa) {
        Profissional profissional = profissionalRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        DiaDisponivel diaDisponivel = new DiaDisponivel();
        diaDisponivel.setEmpresa(empresa);
        diaDisponivel.setProfissional(profissional);
        diaDisponivel.setDia(dto.dia());

        DiaDisponivel saved = diaDisponivelRepository.save(diaDisponivel);
        return mapToResponseDTO(saved);

    }

    // Atualizar
    public DiaDisponivelResponseDTO update(Integer id, DiaDisponivelDTO dto) {
        DiaDisponivel diaDisponivel = diaDisponivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiaDisponivel não encontrado"));

        Profissional profissional = profissionalRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        diaDisponivel.setProfissional(profissional);
        diaDisponivel.setDia(dto.dia());

        DiaDisponivel updated = diaDisponivelRepository.save(diaDisponivel);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Integer id) {
        diaDisponivelRepository.deleteById(id);
    }

    // Mapper interno
    private DiaDisponivelResponseDTO mapToResponseDTO(DiaDisponivel dia) {
        return new DiaDisponivelResponseDTO(
                dia.getId(),
                dia.getProfissional().getId(),
                dia.getDia()
        );
    }
}
