CREATE TABLE livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(255)
);

CREATE TABLE leitor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE emprestimo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATE,
    data_devolucao DATE,
    leitor_id BIGINT,
    CONSTRAINT fk_leitor FOREIGN KEY (leitor_id) REFERENCES leitor(id)
);

CREATE TABLE livro_emprestimo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    livro_id BIGINT,
    emprestimo_id BIGINT,
    quantidade INT,
    CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livro(id),
    CONSTRAINT fk_emprestimo FOREIGN KEY (emprestimo_id) REFERENCES emprestimo(id)
);