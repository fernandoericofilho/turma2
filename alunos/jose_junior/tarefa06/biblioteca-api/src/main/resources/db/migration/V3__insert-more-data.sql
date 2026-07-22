-- Novos leitores
INSERT INTO leitores (nome, email) VALUES ('Carlos Lima', 'carlos@email.com');
INSERT INTO leitores (nome, email) VALUES ('Ana Costa', 'ana@email.com');

-- Novos empréstimos
INSERT INTO emprestimos (data_emprestimo, data_devolucao, leitor_id)
VALUES ('2026-04-03', '2026-04-15', 1);

INSERT INTO emprestimos (data_emprestimo, data_devolucao, leitor_id)
VALUES ('2026-04-04', '2026-04-18', 3);

INSERT INTO emprestimos (data_emprestimo, data_devolucao, leitor_id)
VALUES ('2026-04-05', '2026-04-20', 4);

-- Associação (mesmo livro sendo emprestado várias vezes)
-- Livro 1 (Clean Code) sendo emprestado para vários leitores

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 3, 1); -- João pegou de novo

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 4, 1); -- Carlos pegou

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 5, 1); -- Ana pegou

-- Outros livros misturados
INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 3, 3);

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (2, 4, 2);

INSERT INTO livros_emprestimos (quantidade, emprestimo_id, livro_id)
VALUES (1, 5, 4);