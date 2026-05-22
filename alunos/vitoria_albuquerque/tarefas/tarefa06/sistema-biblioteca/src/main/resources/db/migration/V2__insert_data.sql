INSERT INTO leitor (nome, email) VALUES
    ('Vitória Maria', 'vitoria@email.com'),
    ('João Silva', 'joao@email.com'),
    ('Ana Alves', 'ana@email.com'),
    ('Carla Costa', 'carla@email.com');

INSERT INTO livro (titulo, autor) VALUES
    ('Tudo é rio', 'Carla Madeira'),
    ('Felicidade Clandestina', 'Clarice Lispector'),
    ('Dom Casmurro', 'Machado de Assis'),
    ('Clean Code', 'Robert C. Martin'),
    ('Entendendo Algoritmos', 'Aditya Y. Bhargava');

INSERT INTO emprestimo (data_emprestimo, data_devolucao, leitor_id) VALUES
    ('2026-01-10 10:00:00', '2026-01-20 16:00:00', 1),
    ('2026-01-11 12:00:00', '2026-01-21 17:00:00', 1),
    ('2026-01-12 14:30:00', '2026-01-22 11:00:00', 2),
    ('2026-01-16 09:00:00', NULL, 3),
    ('2026-01-18 16:00:00', NULL, 4);

INSERT INTO livro_emprestimo (id_livro, id_emprestimo, quantidade) VALUES
    (1, 1, 1),
    (2, 1, 1),
    (3, 2, 1),
    (4, 3, 1),
    (5, 3, 1),
    (1, 4, 2);