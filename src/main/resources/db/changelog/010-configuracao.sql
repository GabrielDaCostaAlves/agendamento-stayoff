--liquibase formatted sql

--changeset gabriel:010
CREATE TABLE configuracao (
    id INT PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    config_json JSON,
    CONSTRAINT fk_config_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
