package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.entrada.ProfissionalDTO;
import com.stayoff.agendamento.dto.resposta.ProfissionalResponseDTO;
import com.stayoff.agendamento.dto.paged.ProfissionalPagedDTO;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.model.Profissional;
import com.stayoff.agendamento.model.Servico;
import com.stayoff.agendamento.repository.ProfissionalRepository;
import com.stayoff.agendamento.repository.ServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository, ServicoRepository servicoRepository) {
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }

    // Paginação
    public ProfissionalPagedDTO findAllPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Profissional> profissionalPage = profissionalRepository.findAll(pageable);

        var content = profissionalPage.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new ProfissionalPagedDTO(
                content,
                profissionalPage.getNumber(),
                profissionalPage.getSize(),
                profissionalPage.getTotalElements(),
                profissionalPage.getTotalPages()
        );
    }

    // Buscar por id
    public ProfissionalResponseDTO findById(Integer id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return mapToResponseDTO(profissional);
    }

    // Criar
    public ProfissionalResponseDTO save(ProfissionalDTO dto, Empresa empresa) {
        Profissional profissional = new Profissional();
        profissional.setNome(dto.nome());
        profissional.setEmpresa(empresa);


        List<Servico> servicos = servicoRepository.findAllById(dto.servicoIds());
        profissional.setServicos(servicos);

        Profissional saved = profissionalRepository.save(profissional);
        return mapToResponseDTO(saved);
    }

    // Atualizar
    public ProfissionalResponseDTO update(Integer id, ProfissionalDTO dto) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        profissional.setNome(dto.nome());


        List<Servico> servicos = servicoRepository.findAllById(dto.servicoIds());
        profissional.setServicos(servicos);

        Profissional updated = profissionalRepository.save(profissional);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Integer id) {
        profissionalRepository.deleteById(id);
    }

    // Mapper interno
    private ProfissionalResponseDTO mapToResponseDTO(Profissional profissional) {
        List<String> nomesServicos = profissional.getServicos().stream()
                .map(Servico::getNome)
                .collect(Collectors.toList());

        return new ProfissionalResponseDTO(
                profissional.getId(),
                profissional.getNome(),
                nomesServicos
        );
    }
}
