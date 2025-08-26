package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.entrada.ClienteDTO;
import com.stayoff.agendamento.dto.resposta.ClienteResponseDTO;
import com.stayoff.agendamento.dto.paged.ClientePagedDTO;
import com.stayoff.agendamento.exception.ResourceNotFoundException;
import com.stayoff.agendamento.model.Cliente;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Paginação
    public ClientePagedDTO findAllPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Cliente> clientePage = clienteRepository.findAll(pageable);

        var content = clientePage.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new ClientePagedDTO(
                content,
                clientePage.getNumber(),
                clientePage.getSize(),
                clientePage.getTotalElements(),
                clientePage.getTotalPages()
        );
    }

    // Buscar por id
    public ClienteResponseDTO findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
        return mapToResponseDTO(cliente);
    }

    // Criar
    public ClienteResponseDTO save(ClienteDTO dto, Empresa empresa) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setSenha(dto.senha());
        cliente.setEmpresa(empresa);
        cliente.setAtivo(true);

        Cliente saved = clienteRepository.save(cliente);
        return mapToResponseDTO(saved);
    }

    // Atualizar
    public ClienteResponseDTO update(Integer id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setSenha(dto.senha());

        Cliente updated = clienteRepository.save(cliente);
        return mapToResponseDTO(updated);
    }

    // Deletar
    public void delete(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
        clienteRepository.delete(cliente);
    }

    // Mapper interno
    private ClienteResponseDTO mapToResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getEmpresa().getId().intValue(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}
