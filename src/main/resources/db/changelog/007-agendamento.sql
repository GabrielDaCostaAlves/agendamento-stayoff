--liquibase formatted sql

--changeset gabriel:007
CREATE TABLE agendamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    inicio TIMESTAMP NOT NULL,
    fim TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    motivo_cancelamento TEXT,
    empresa_id BIGINT NOT NULL,
    valor_total DECIMAL(10,2),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_cancelamento TIMESTAMP,
    data_confirmacao TIMESTAMP,
    CONSTRAINT fk_agendamento_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_agendamento_profissional FOREIGN KEY (profissional_id) REFERENCES profissional(id),
    CONSTRAINT fk_agendamento_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);
