/*
 * Seleciona o schema/banco de dados
 */
\c turma2;

/*
 * Cria as tabelas
 */
CREATE TABLE departamento (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE funcionario (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    departamento_id INTEGER,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

/*
 * Insere dados nas tabelas
 */
INSERT INTO departamento (nome) VALUES ('Recursos Humanos');
INSERT INTO departamento (nome) VALUES ('Tecnologia da Informação');

INSERT INTO funcionario (nome, departamento_id) VALUES ('Alice', 1);
INSERT INTO funcionario (nome, departamento_id) VALUES ('Marcos', 2);
INSERT INTO funcionario (nome, departamento_id) VALUES ('Maria', 1);
INSERT INTO funcionario (nome, departamento_id) VALUES ('David', 2);
INSERT INTO funcionario (nome) VALUES ('Carlos');
INSERT INTO funcionario (nome) VALUES ('Yasmim');

/*
 * Consulta os funcionários e seus departamentos
 */
SELECT
    funcionario.id AS funcionario_id,
    funcionario.nome AS funcionario_nome,
    departamento.id AS departamento_id,
    departamento.nome AS departamento_nome
FROM funcionario
LEFT JOIN departamento ON
    funcionario.departamento_id = departamento.id;