CREATE TABLE alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL
);

CREATE TABLE professores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL
);

CREATE TABLE disciplinas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    professor_id INT NOT NULL,
    CONSTRAINT fk_disciplinas_professores
        FOREIGN KEY (professor_id)
        REFERENCES professores(id)
);

CREATE TABLE alunos_disciplinas (
    aluno_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    data_matricula DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (aluno_id, disciplina_id),
    CONSTRAINT fk_alunos_disciplinas_aluno
        FOREIGN KEY (aluno_id)
        REFERENCES alunos(id),
    CONSTRAINT fk_alunos_disciplinas_disciplina
        FOREIGN KEY (disciplina_id)
        REFERENCES disciplinas(id)
);

INSERT INTO professores (nome) VALUES
    ('Fernanda Melo'),
    ('Ricardo Nunes');

INSERT INTO disciplinas (nome, professor_id) VALUES
    ('Banco de Dados', 1),
    ('Algoritmos', 2),
    ('Modelagem de Sistemas', 1);

INSERT INTO alunos (nome) VALUES
    ('Livia Santos'),
    ('Carlos Eduardo'),
    ('Bianca Araujo');

INSERT INTO alunos_disciplinas (aluno_id, disciplina_id) VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 3),
    (3, 2);

SELECT
    a.id AS aluno_id,
    a.nome AS aluno,
    d.nome AS disciplina,
    p.id AS professor_id,
    p.nome AS professor
FROM alunos_disciplinas ad
INNER JOIN alunos a ON a.id = ad.aluno_id
INNER JOIN disciplinas d ON d.id = ad.disciplina_id
INNER JOIN professores p ON p.id = d.professor_id;
