CREATE TABLE pacientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(20) NOT NULL,
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(20),
    complemento VARCHAR(255),
    numero VARCHAR(20),
    uf VARCHAR(2),
    cidade VARCHAR(255),
    ativo BOOLEAN NOT NULL
);