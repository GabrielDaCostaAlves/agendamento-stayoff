package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.AgendamentoDTO;
import com.stayoff.agendamento.dto.AgendamentoResponseDTO;
import com.stayoff.agendamento.dto.AgendamentoPagedDTO;
import com.stayoff.agendamento.model.Agendamento;
import com.stayoff.agendamento.model.Cliente;
import com.stayoff.agendamento.model.Profissional;
import com.stayoff.agendamento.model.Servico;
import com.stayoff.agendamento.repository.AgendamentoRepository;
import com.stayoff.agendamento.repository.ClienteRepository;
import com.stayoff.agendamento.repository.ProfissionalRepository;
import com.stayoff.agendamento.repository.ServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository,
            ProfissionalRepository profissionalRepository,
            ServicoRepository servicoRepository
    ) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }

    // Paginação
    public AgendamentoPagedDTO findAllPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Agendamento> agendamentoPage = agendamentoRepository.findAll(pageable);

        List<AgendamentoResponseDTO> content = agendamentoPage.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new AgendamentoPagedDTO(
                content,
                agendamentoPage.getNumber(),
                agendamentoPage.getSize(),
                agendamentoPage.getTotalElements(),
                agendamentoPage.getTotalPages()
        );
    }

    // Buscar por id
    public AgendamentoResponseDTO findById(Integer id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return mapToResponseDTO(agendamento);
    }

    // Criar
    public AgendamentoResponseDTO save(AgendamentoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Profissional profissional = profissionalRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        List<Servico> servicos = servicoRepository.findAllById(dto.servicoIds());

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setEmpresa(profissional.getEmpresa());
        agendamento.setInicio(dto.inicio());
        agendamento.setFim(dto.fim());
        agendamento.setMotivoCancelamento(dto.motivoCancelamento());
        agendamento.setValorTotal(dto.valorTotal());
        agendamento.setCriadoEm(LocalDateTime.now());
        agendamento.setStatus("PENDENTE");
        agendamento.setServicos(servicos);

        Agendamento saved = agendamentoRepository.save(agendamento);
        return mapToResponseDTO(saved);
    }

    // Atualizar
    public AgendamentoResponseDTO update(Integer id, AgendamentoDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Profissional profissional = profissionalRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        List<Servico> servicos = servicoRepository.findAllById(dto.servicoIds());

        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setEmpresa(profissional.getEmpresa());
        agendamento.setInicio(dto.inicio());
        agendamento.setFim(dto.fim());
        agendamento.setMotivoCancelamento(dto.motivoCancelamento());
        agendamento.setValorTotal(dto.valorTotal());
        agendamento.setAtualizadoEm(LocalDateTime.now());
        agendamento.setServicos(servicos);

        Agendamento updated = agendamentoRepository.save(agendamento);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Integer id) {
        agendamentoRepository.deleteById(id);
    }

    // Mapper interno
    private AgendamentoResponseDTO mapToResponseDTO(Agendamento agendamento) {
        List<Integer> servicoIds = agendamento.getServicos().stream()
                .map(Servico::getId)
                .collect(Collectors.toList());

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getCliente().getId(),
                agendamento.getProfissional().getId(),
                agendamento.getInicio(),
                agendamento.getFim(),
                agendamento.getStatus(),
                agendamento.getMotivoCancelamento(),
                agendamento.getValorTotal(),
                agendamento.getCriadoEm(),
                agendamento.getAtualizadoEm(),
                agendamento.getDataCancelamento(),
                agendamento.getDataConfirmacao(),
                servicoIds
        );
    }
}
