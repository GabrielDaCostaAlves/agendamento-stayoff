--liquibase formatted sql

--changeset gabriel:009
CREATE TABLE profissional_servico (
    profissional_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,
    PRIMARY KEY (profissional_id, servico_id),
    CONSTRAINT fk_ps_profissional FOREIGN KEY (profissional_id) REFERENCES profissional(id),
    CONSTRAINT fk_ps_servico FOREIGN KEY (servico_id) REFERENCES servico(id)
);
