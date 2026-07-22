-- Inserindo leitores
INSERT INTO leitor (id, nome, email) VALUES (1, 'João Silva', 'joao@email.com');
INSERT INTO leitor (id, nome, email) VALUES (2, 'Maria Souza', 'maria@email.com');

-- Inserindo livros
INSERT INTO livro (id, titulo, autor) VALUES (1, 'Clean Code', 'Robert Martin');
INSERT INTO livro (id, titulo, autor) VALUES (2, 'Java Efetivo', 'Joshua Bloch');
INSERT INTO livro (id, titulo, autor) VALUES (3, 'O Senhor dos Anéis', 'J.R.R. Tolkien');
INSERT INTO livro (id, titulo, autor) VALUES (4, 'Dom Casmurro', 'Machado de Assis');

-- Inserindo empréstimos
INSERT INTO emprestimo (id, data_emprestimo, data_devolucao, leitor_id)
VALUES (1, '2026-04-10', '2026-04-20', 1);

INSERT INTO emprestimo (id, data_emprestimo, data_devolucao, leitor_id)
VALUES (2, '2026-04-11', '2026-04-25', 2);

-- Relacionando livros com empréstimos
INSERT INTO livro_emprestimo (id, quantidade, livro_id, emprestimo_id)
VALUES (1, 1, 1, 1);

INSERT INTO livro_emprestimo (id, quantidade, livro_id, emprestimo_id)
VALUES (2, 2, 2, 1);

INSERT INTO livro_emprestimo (id, quantidade, livro_id, emprestimo_id)
VALUES (3, 1, 3, 2);

INSERT INTO livro_emprestimo (id, quantidade, livro_id, emprestimo_id)
VALUES (4, 1, 4, 2);