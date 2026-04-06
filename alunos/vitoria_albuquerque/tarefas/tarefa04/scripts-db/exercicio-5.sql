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
    nome VARCHAR(255) NOT NULL,
    professor_id INTEGER NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE aluno_disciplina (
    aluno_id INTEGER NOT NULL,
    disciplina_id INTEGER NOT NULL,
    PRIMARY KEY (aluno_id, disciplina_id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);

INSERT INTO professor (nome) VALUES ('Fernando');
INSERT INTO professor (nome) VALUES ('Carla');

INSERT INTO disciplina (nome, professor_id) VALUES ('Algoritmos', 1);
INSERT INTO disciplina (nome, professor_id) VALUES ('Cálculo I', 2);
INSERT INTO disciplina (nome, professor_id) VALUES ('Estruturas de Dados', 1);

INSERT INTO aluno (nome) VALUES ('Gabriel');
INSERT INTO aluno (nome) VALUES ('Bruna');
INSERT INTO aluno (nome) VALUES ('Diego');

INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (1, 1);
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (1, 2);
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (2, 1);
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (3, 2);
INSERT INTO aluno_disciplina (aluno_id, disciplina_id) VALUES (3, 3);