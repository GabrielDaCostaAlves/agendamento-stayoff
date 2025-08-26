package com.stayoff.agendamento.service;

import com.stayoff.agendamento.dto.ConfiguracaoDTO;
import com.stayoff.agendamento.dto.ConfiguracaoResponseDTO;
import com.stayoff.agendamento.model.Configuracao;
import com.stayoff.agendamento.model.Empresa;
import com.stayoff.agendamento.repository.ConfiguracaoRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    // Buscar configuração da empresa
    public ConfiguracaoResponseDTO findByEmpresa(Empresa empresa) {
        Configuracao configuracao = configuracaoRepository
                .findByEmpresa(empresa)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada"));
        return mapToResponseDTO(configuracao);
    }

    // Criar ou atualizar configuração
    public ConfiguracaoResponseDTO saveOrUpdate(ConfiguracaoDTO dto, Empresa empresa) {
        Configuracao configuracao = configuracaoRepository
                .findByEmpresa(empresa)
                .orElse(new Configuracao());

        configuracao.setEmpresa(empresa);
        configuracao.setConfigJson(dto.configJson());

        Configuracao saved = configuracaoRepository.save(configuracao);
        return mapToResponseDTO(saved);
    }

    // Mapper interno
    private ConfiguracaoResponseDTO mapToResponseDTO(Configuracao configuracao) {
        return new ConfiguracaoResponseDTO(
                configuracao.getConfigJson()
        );
    }
}
