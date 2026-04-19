-- Criação da tabela Livros

CREATE TABLE livros(
    id BIGINT AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

-- Criação da tabela Leitores

CREATE TABLE leitores(
    id BIGINT AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,

    PRIMARY KEY (id)
);

-- Criação da tabela Emprestimos

CREATE TABLE emprestimos(
    id BIGINT AUTO_INCREMENT,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    leitor_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_emprestimo_leitor
        FOREIGN KEY (leitor_id)
            REFERENCES leitores (id)
);

-- Criação da tabela Livros_Emprestimos

CREATE TABLE livros_emprestimos(
    id BIGINT AUTO_INCREMENT,
    quantidade INT NOT NULL,
    emprestimo_id BIGINT NOT NULL,
    livro_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_livros_emprestimos_emprestimo
        FOREIGN KEY (emprestimo_id)
            REFERENCES emprestimos (id),

    CONSTRAINT fk_livros_emprestimos_livro
        FOREIGN KEY (livro_id)
            REFERENCES livros (id),

    CONSTRAINT unique_livro_emprestimo
        UNIQUE (livro_id, emprestimo_id)
);