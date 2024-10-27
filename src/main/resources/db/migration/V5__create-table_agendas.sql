CREATE TABLE agendas (
    id BIGSERIAL NOT NULL,
    id_medico BIGINT NOT NULL,
    id_paciente BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    descricao VARCHAR(255),
    CONSTRAINT fk_medico FOREIGN KEY (id_medico) REFERENCES medicos(id),
    CONSTRAINT fk_paciente FOREIGN KEY (id_paciente) REFERENCES pacientes(id)
);