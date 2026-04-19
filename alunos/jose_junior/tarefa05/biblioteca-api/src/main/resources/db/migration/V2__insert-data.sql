-- Inserindo dados na tabela Livros
INSERT INTO livros (titulo, autor) VALUES ('Clean Code', 'Robert C. Martin');
INSERT INTO livros (titulo, autor) VALUES ('Effective Java', 'Joshua Bloch');
INSERT INTO livros (titulo, autor) VALUES ('Design Patterns', 'Erich Gamma');
INSERT INTO livros (titulo, autor) VALUES ('Refactoring', 'Martin Fowler');

-- Inserindo dados na tabela Leitores
INSERT INTO leitores (nome, email) VALUES ('João Silva', 'joao@email.com');
INSERT INTO leitores (nome, email) VALUES ('Maria Souza', 'maria@email.com');

-- Inserindo dados na tabela Emprestimos
INSERT INTO emprestimos (data_emprestimo, data_devolucao, leitor_id)
VALUES ('2026-04-01', '2026-04-10', 1);

INSERT INTO emprestimos (data_emprestimo, data_devolucao, leitor_id)
VALUES ('2026-04-02', '2026-04-12', 2);

-- Inserindo dados na tabela Livros_Emprestimos
INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 1, 1);

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (2, 1, 2);

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 2, 3);

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 2, 4);