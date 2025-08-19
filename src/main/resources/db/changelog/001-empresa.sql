--liquibase formatted sql

--changeset gabriel:001
CREATE TABLE empresa (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(50),
    endereco VARCHAR(500),
    subdominio VARCHAR(100),
    status_conta VARCHAR(50),
    data_expiracao TIMESTAMP,
    data_ultimo_pagamento TIMESTAMP,
    configuracoes JSON,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
