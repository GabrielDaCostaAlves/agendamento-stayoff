--liquibase formatted sql

--changeset gabriel:006
CREATE TABLE bloco_disponibilidade (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    dia_disponivel_id BIGINT NOT NULL,
    inicio TIMESTAMP NOT NULL,
    fim TIMESTAMP NOT NULL,
    CONSTRAINT fk_bloco_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    CONSTRAINT fk_bloco_dia FOREIGN KEY (dia_disponivel_id) REFERENCES dia_disponivel(id)
);
