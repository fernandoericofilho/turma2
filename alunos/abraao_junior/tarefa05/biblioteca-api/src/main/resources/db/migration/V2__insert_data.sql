INSERT INTO leitor (nome, email)
VALUES ('José Rodrigues', 'jose@email.com'),
       ('Joana Silva', 'joana@email.com'),
       ('Carlos Pereira', 'carlos@email.com');


INSERT INTO livro (titulo, autor, estoque)
VALUES ('Entendendo Algoritmos', 'Aditya Bhargava', 10),
       ('O Programador Pragmático', 'Andrew Hunt & David Thomas', 5),
       ('Design Patterns', 'GoF', 7),
       ('O Programador Apaixonado', 'Chad Fowler', 3);


INSERT INTO emprestimo (data_emprestimo, data_devolucao, leitor_id)
VALUES ('2026-04-01', '2026-04-08', 1),
       ('2026-04-02', '2026-04-10', 2),
       ('2026-04-03', '2026-04-09', 3);


INSERT INTO livro_emprestimo (livro_id, emprestimo_id, quantidade)
VALUES (1, 1, 2),
       (2, 1, 1),

       (3, 2, 1),
       (4, 2, 1),

       (1, 3, 1),
       (3, 3, 2);