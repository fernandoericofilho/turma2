CREATE TABLE leitor (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE livro (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL
);

CREATE TABLE emprestimo (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    data_emprestimo TIMESTAMP,
    data_devolucao TIMESTAMP,
    leitor_id BIGINT NOT NULL,
    FOREIGN KEY (leitor_id) REFERENCES leitor(id)
);

CREATE TABLE livro_emprestimo (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_livro BIGINT NOT NULL,
    id_emprestimo BIGINT NOT NULL,
    quantidade INTEGER,
    FOREIGN KEY (id_livro) REFERENCES livro(id),
    FOREIGN KEY (id_emprestimo) REFERENCES emprestimo(id)
);