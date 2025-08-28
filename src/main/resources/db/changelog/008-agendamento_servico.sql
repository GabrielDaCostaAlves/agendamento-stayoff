--liquibase formatted sql

--changeset gabriel:008
CREATE TABLE agendamento_servico (
    agendamento_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,
    PRIMARY KEY (agendamento_id, servico_id),
    CONSTRAINT fk_as_agendamento FOREIGN KEY (agendamento_id) REFERENCES agendamento(id),
    CONSTRAINT fk_as_servico FOREIGN KEY (servico_id) REFERENCES servico(id)
);
