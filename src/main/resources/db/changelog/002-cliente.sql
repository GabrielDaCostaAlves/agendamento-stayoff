--liquibase formatted sql

--changeset gabriel:002
CREATE TABLE cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(50),
    senha VARCHAR(255) NOT NULL,
    roles VARCHAR(50) DEFAULT 'ROLE_CLIENTE',
    ativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_cliente_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
