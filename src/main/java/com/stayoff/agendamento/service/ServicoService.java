package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.entrada.ServicoDTO;
import com.stayoff.agendamento.dto.resposta.ServicoResponseDTO;
import com.stayoff.agendamento.dto.paged.ServicoPagedDTO;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.model.Servico;
import com.stayoff.agendamento.repository.ServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    // Paginação
    public ServicoPagedDTO findAllPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Servico> servicoPage = servicoRepository.findAll(pageable);

        var content = servicoPage.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new ServicoPagedDTO(
                content,
                servicoPage.getNumber(),
                servicoPage.getSize(),
                servicoPage.getTotalElements(),
                servicoPage.getTotalPages()
        );
    }

    // Buscar por id
    public ServicoResponseDTO findById(Integer id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        return mapToResponseDTO(servico);
    }

    // Criar
    public ServicoResponseDTO save(ServicoDTO dto, Empresa empresa) {
        Servico servico = new Servico();
        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setDuracao(dto.duracao());
        servico.setPreco(dto.preco());
        servico.setCategoria(dto.categoria());
        servico.setEmpresa(empresa);

        Servico saved = servicoRepository.save(servico);
        return mapToResponseDTO(saved);
    }

    // Atualizar
    public ServicoResponseDTO update(Integer id, ServicoDTO dto) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setDuracao(dto.duracao());
        servico.setPreco(dto.preco());
        servico.setCategoria(dto.categoria());

        Servico updated = servicoRepository.save(servico);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Integer id) {
        servicoRepository.deleteById(id);
    }

    // Mapper interno
    private ServicoResponseDTO mapToResponseDTO(Servico servico) {
        return new ServicoResponseDTO(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getDuracao(),
                servico.getPreco(),
                servico.getCategoria()
        );
    }
}
