package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.entrada.EmpresaDTO;
import com.stayoff.agendamento.dto.resposta.EmpresaResponseDTO;
import com.stayoff.agendamento.dto.paged.EmpresaPagedDTO;
import com.stayoff.agendamento.exception.ResourceNotFoundException;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.repository.EmpresaRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // Paginação
    public EmpresaPagedDTO findAllPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Empresa> empresaPage = empresaRepository.findAll(pageable);

        var content = empresaPage.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new EmpresaPagedDTO(
                content,
                empresaPage.getNumber(),
                empresaPage.getSize(),
                empresaPage.getTotalElements(),
                empresaPage.getTotalPages()
        );
    }

    // Buscar por id
    public EmpresaResponseDTO findById(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com id: " + id));
        return mapToResponseDTO(empresa);
    }

    // Criar
    public EmpresaResponseDTO save(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNome(dto.nome());
        empresa.setEmail(dto.email());
        empresa.setTelefone(dto.telefone());
        empresa.setEndereco(dto.endereco());
        empresa.setSubdominio(dto.subdominio());
        empresa.setDataCriacao(LocalDateTime.now());
        empresa.setAtivo(true);

        Empresa saved = empresaRepository.save(empresa);
        return mapToResponseDTO(saved);
    }

    // Atualizar
    public EmpresaResponseDTO update(Long id, EmpresaDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com id: " + id));

        empresa.setNome(dto.nome());
        empresa.setEmail(dto.email());
        empresa.setTelefone(dto.telefone());
        empresa.setEndereco(dto.endereco());
        empresa.setSubdominio(dto.subdominio());
        empresa.setDataAtualizacao(LocalDateTime.now());

        Empresa updated = empresaRepository.save(empresa);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empresa não encontrada com id: " + id);
        }
        empresaRepository.deleteById(id);
    }

    // Mapper interno
    private EmpresaResponseDTO mapToResponseDTO(Empresa empresa) {
        return new EmpresaResponseDTO(
                empresa.getNome(),
                empresa.getEmail(),
                empresa.getTelefone(),
                empresa.getEndereco(),
                empresa.getSubdominio()
        );
    }
}
