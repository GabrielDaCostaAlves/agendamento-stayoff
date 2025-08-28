--liquibase formatted sql

--changeset gabriel:004
CREATE TABLE servico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    duracao INT,
    preco DECIMAL(10,2),
    categoria VARCHAR(100),
    CONSTRAINT fk_servico_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
