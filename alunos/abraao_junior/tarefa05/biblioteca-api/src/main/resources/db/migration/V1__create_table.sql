CREATE TABLE leitor
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE
);

CREATE TABLE livro
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    estoque INT NOT NULL,

    CONSTRAINT chk_estoque_positivo CHECK (estoque >= 0)
);

CREATE TABLE emprestimo
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,

    leitor_id BIGINT NOT NULL,

    CONSTRAINT fk_emprestimo_leitor
        FOREIGN KEY (leitor_id) REFERENCES leitor (id),

    CONSTRAINT chk_datas_validas CHECK (
        data_devolucao >= data_emprestimo
    )
);

CREATE TABLE livro_emprestimo
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    livro_id BIGINT NOT NULL,
    emprestimo_id BIGINT NOT NULL,
    quantidade INT NOT NULL,

    CONSTRAINT fk_livro
        FOREIGN KEY (livro_id) REFERENCES livro (id),

    CONSTRAINT fk_emprestimo
        FOREIGN KEY (emprestimo_id) REFERENCES emprestimo (id),

    CONSTRAINT chk_quantidade CHECK (quantidade > 0)
);