-- Criação das tabelas Departamentos e Funcionários

CREATE TABLE Departamentos(
    id SERIAL,
    nome TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE Funcionarios(
    id SERIAL,
    nome TEXT,
    cargo TEXT,
    departamento_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_departamento_funcionarios
        FOREIGN KEY (departamento_id)
        REFERENCES Departamentos(id)
);