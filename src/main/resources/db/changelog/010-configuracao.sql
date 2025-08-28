--liquibase formatted sql

--changeset gabriel:010
CREATE TABLE configuracao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    config_json JSON,
    CONSTRAINT fk_config_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
