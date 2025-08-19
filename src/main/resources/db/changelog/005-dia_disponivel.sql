--liquibase formatted sql

--changeset gabriel:005
CREATE TABLE dia_disponivel (
    id INT PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    profissional_id INT NOT NULL,
    dia DATE NOT NULL,
    CONSTRAINT fk_dia_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    CONSTRAINT fk_dia_profissional FOREIGN KEY (profissional_id) REFERENCES profissional(id)
);
