--liquibase formatted sql

--changeset gabriel:002
CREATE TABLE cliente (
    id INT PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(50),
    CONSTRAINT fk_cliente_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
