/*
 * Seleciona o schema/banco de dados
 */
\c turma2;

/*
 * Cria as tabelas
 */
CREATE TABLE aluno (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE professor (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE disciplina (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE aluno_disciplina (
    aluno_id INTEGER NOT NULL,
    disciplina_id INTEGER NOT NULL,

    PRIMARY KEY (aluno_id, disciplina_id),

    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);

CREATE TABLE professor_disciplina (
    professor_id INTEGER NOT NULL,
    disciplina_id INTEGER NOT NULL,

    PRIMARY KEY (professor_id, disciplina_id),

    FOREIGN KEY (professor_id) REFERENCES professor(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);

/*
 * Insere os dados
 */
INSERT INTO aluno (nome)
VALUES
    ('Ana'),
    ('Bruno'),
    ('Carla'),
    ('Diego');

INSERT INTO professor (nome)
VALUES
    ('Ricardo'),
    ('Maria'),
    ('Roberto');

INSERT INTO disciplina (nome)
VALUES
    ('Desenvolvimento Web'),
    ('Banco de Dados'),
    ('Algoritmos e Estruturas de Dados');

INSERT INTO aluno_disciplina (aluno_id, disciplina_id)
    VALUES
        (1, 1),
        (1, 2),
        (2, 2),
        (3, 1),
        (3, 3);

INSERT INTO professor_disciplina (professor_id, disciplina_id)
    VALUES
        (1, 1),
        (2, 2),
        (3, 3);

/*
 * Consulta os dados
 */
SELECT
    aluno.nome AS aluno_nome,
    disciplina.nome AS disciplina_nome,
    professor.nome AS professor_nome
FROM aluno
LEFT JOIN aluno_disciplina ON
    aluno.id = aluno_disciplina.aluno_id
LEFT JOIN disciplina ON
    aluno_disciplina.disciplina_id = disciplina.id
LEFT JOIN professor_disciplina ON
    disciplina.id = professor_disciplina.disciplina_id
LEFT JOIN professor ON
    professor_disciplina.professor_id = professor.id
ORDER BY aluno.nome, disciplina.nome;