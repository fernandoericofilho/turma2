CREATE TABLE departamentos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL
);

CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cargo VARCHAR(80) NOT NULL,
    departamento_id INT NOT NULL,
    CONSTRAINT fk_funcionarios_departamentos
        FOREIGN KEY (departamento_id)
        REFERENCES departamentos(id)
);

INSERT INTO departamentos (nome) VALUES
    ('Financeiro'),
    ('Tecnologia'),
    ('RH');

INSERT INTO funcionarios (nome, cargo, departamento_id) VALUES
    ('Marcos Alves', 'Analista Financeiro', 1),
    ('Juliana Rocha', 'Desenvolvedora', 2),
    ('Paulo Mendes', 'Recrutador', 3),
    ('Renata Costa', 'Suporte', 2);

SELECT
    f.id AS funcionario_id,
    f.nome AS funcionario,
    f.cargo,
    d.id AS departamento_id,
    d.nome AS departamento
FROM funcionarios f
INNER JOIN departamentos d ON d.id = f.departamento_id;