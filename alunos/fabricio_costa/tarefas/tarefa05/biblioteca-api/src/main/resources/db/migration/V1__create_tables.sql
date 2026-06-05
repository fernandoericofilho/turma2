-- ============================================================
-- V1__criar_tabelas.sql
-- Migração inicial: criação das tabelas do sistema de biblioteca
-- ============================================================

-- Tabela de Leitores
CREATE TABLE leitor (
                        id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome       VARCHAR(150) NOT NULL,
                        email      VARCHAR(200) NOT NULL UNIQUE
);

-- Tabela de Livros
CREATE TABLE livro (
                       id     BIGINT AUTO_INCREMENT PRIMARY KEY,
                       titulo VARCHAR(200) NOT NULL,
                       autor  VARCHAR(150) NOT NULL
);

-- Tabela de Empréstimos (1 leitor : N empréstimos)
CREATE TABLE emprestimo (
                            id               BIGINT AUTO_INCREMENT PRIMARY KEY,
                            data_emprestimo  DATE NOT NULL,
                            data_devolucao   DATE,
                            leitor_id        BIGINT NOT NULL,
                            CONSTRAINT fk_emprestimo_leitor FOREIGN KEY (leitor_id) REFERENCES leitor(id)
);

-- Tabela intermediária Livro x Empréstimo (N:N com quantidade)
CREATE TABLE livro_emprestimo (
                                  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  quantidade     INT NOT NULL DEFAULT 1,
                                  livro_id       BIGINT NOT NULL,
                                  emprestimo_id  BIGINT NOT NULL,
                                  CONSTRAINT fk_le_livro      FOREIGN KEY (livro_id)      REFERENCES livro(id),
                                  CONSTRAINT fk_le_emprestimo FOREIGN KEY (emprestimo_id) REFERENCES emprestimo(id)
);