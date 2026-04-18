CREATE TABLE leitor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL
);

CREATE TABLE emprestimo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo VARCHAR(50),
    data_devolucao VARCHAR(50),
    leitor_id BIGINT,
    CONSTRAINT fk_leitor
        FOREIGN KEY (leitor_id)
        REFERENCES leitor(id)
);

CREATE TABLE livro_emprestimo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT,
    livro_id BIGINT,
    emprestimo_id BIGINT,
    CONSTRAINT fk_livro
        FOREIGN KEY (livro_id)
        REFERENCES livro(id),
    CONSTRAINT fk_emprestimo
        FOREIGN KEY (emprestimo_id)
        REFERENCES emprestimo(id)
);