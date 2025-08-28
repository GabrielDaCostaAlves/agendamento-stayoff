--liquibase formatted sql

--changeset gabriel:003
CREATE TABLE profissional (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    CONSTRAINT fk_profissional_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
