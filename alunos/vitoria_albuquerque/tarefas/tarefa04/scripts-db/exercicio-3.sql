CREATE TABLE departamento (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE funcionario (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    departamento_id INTEGER NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

INSERT INTO departamento (nome) VALUES ('Recursos Humanos');
INSERT INTO departamento (nome) VALUES ('TI');
INSERT INTO departamento (nome) VALUES ('Marketing');

INSERT INTO funcionario (nome, departamento_id) VALUES ('João', 1);
INSERT INTO funcionario (nome, departamento_id) VALUES ('Ana', 1);
INSERT INTO funcionario (nome, departamento_id) VALUES ('Beatriz', 2);