package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.entrada.BlocoDisponibilidadeDTO;
import com.stayoff.agendamento.dto.resposta.BlocoDisponibilidadeResponseDTO;
import com.stayoff.agendamento.dto.paged.BlocoDisponibilidadePagedDTO;
import com.stayoff.agendamento.exception.ResourceNotFoundException;
import com.stayoff.agendamento.model.BlocoDisponibilidade;
import com.stayoff.agendamento.model.DiaDisponivel;
import com.stayoff.agendamento.repository.BlocoDisponibilidadeRepository;
import com.stayoff.agendamento.repository.DiaDisponivelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlocoDisponibilidadeService {

    private final BlocoDisponibilidadeRepository blocoRepository;
    private final DiaDisponivelRepository diaRepository;

    public BlocoDisponibilidadeService(
            BlocoDisponibilidadeRepository blocoRepository,
            DiaDisponivelRepository diaRepository
    ) {
        this.blocoRepository = blocoRepository;
        this.diaRepository = diaRepository;
    }

    // Paginação
    public BlocoDisponibilidadePagedDTO findAllPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BlocoDisponibilidade> blocoPage = blocoRepository.findAll(pageable);

        List<BlocoDisponibilidadeResponseDTO> content = blocoPage.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new BlocoDisponibilidadePagedDTO(
                content,
                blocoPage.getNumber(),
                blocoPage.getSize(),
                blocoPage.getTotalElements(),
                blocoPage.getTotalPages()
        );
    }

    // Buscar por id
    public BlocoDisponibilidadeResponseDTO findById(Integer id) {
        BlocoDisponibilidade bloco = blocoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bloco de disponibilidade não encontrado com id: " + id));
        return mapToResponseDTO(bloco);
    }

    // Criar
    public BlocoDisponibilidadeResponseDTO save(BlocoDisponibilidadeDTO dto) {
        DiaDisponivel dia = diaRepository.findById(dto.diaDisponivelId())
                .orElseThrow(() -> new ResourceNotFoundException("DiaDisponivel não encontrado com id: " + dto.diaDisponivelId()));

        BlocoDisponibilidade bloco = new BlocoDisponibilidade();
        bloco.setDiaDisponivel(dia);
        bloco.setEmpresa(dia.getEmpresa());
        bloco.setInicio(dto.inicio());
        bloco.setFim(dto.fim());

        BlocoDisponibilidade saved = blocoRepository.save(bloco);
        return mapToResponseDTO(saved);
    }

    // Atualizar
    public BlocoDisponibilidadeResponseDTO update(Integer id, BlocoDisponibilidadeDTO dto) {
        BlocoDisponibilidade bloco = blocoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bloco de disponibilidade não encontrado com id: " + id));

        DiaDisponivel dia = diaRepository.findById(dto.diaDisponivelId())
                .orElseThrow(() -> new ResourceNotFoundException("DiaDisponivel não encontrado com id: " + dto.diaDisponivelId()));

        bloco.setDiaDisponivel(dia);
        bloco.setEmpresa(dia.getEmpresa());
        bloco.setInicio(dto.inicio());
        bloco.setFim(dto.fim());

        BlocoDisponibilidade updated = blocoRepository.save(bloco);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Integer id) {
        BlocoDisponibilidade bloco = blocoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bloco de disponibilidade não encontrado com id: " + id));
        blocoRepository.delete(bloco);
    }

    // Mapper interno
    private BlocoDisponibilidadeResponseDTO mapToResponseDTO(BlocoDisponibilidade bloco) {
        return new BlocoDisponibilidadeResponseDTO(
                bloco.getId(),
                bloco.getDiaDisponivel().getId(),
                bloco.getInicio(),
                bloco.getFim()
        );
    }
}
