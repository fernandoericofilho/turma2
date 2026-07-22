-- ============================================================
-- V2__inserir_dados_exemplo.sql
-- Dados iniciais: leitores, livros e empréstimos de exemplo
-- ============================================================

-- Leitores
INSERT INTO leitor (nome, email) VALUES
                                     ('Ana Silva',    'ana.silva@email.com'),
                                     ('Bruno Souza',  'bruno.souza@email.com'),
                                     ('Carla Mendes', 'carla.mendes@email.com');

-- Livros
INSERT INTO livro (titulo, autor) VALUES
                                      ('Clean Code',                   'Robert C. Martin'),
                                      ('Domain-Driven Design',         'Eric Evans'),
                                      ('Refactoring',                  'Martin Fowler'),
                                      ('The Pragmatic Programmer',     'Andrew Hunt'),
                                      ('Design Patterns',              'Gang of Four'),
                                      ('Spring in Action',             'Craig Walls');

-- Empréstimos
INSERT INTO emprestimo (data_emprestimo, data_devolucao, leitor_id) VALUES
                                                                        ('2024-01-10', '2024-01-24', 1),  -- Ana: devolvido
                                                                        ('2024-02-05', NULL,         1),  -- Ana: em aberto
                                                                        ('2024-01-15', '2024-01-29', 2),  -- Bruno: devolvido
                                                                        ('2024-03-01', NULL,         3);  -- Carla: em aberto

-- LivroEmprestimo (relacionamento N:N)
INSERT INTO livro_emprestimo (quantidade, livro_id, emprestimo_id) VALUES
                                                                       (1, 1, 1),  -- Clean Code → empréstimo 1 (Ana)
                                                                       (1, 2, 1),  -- DDD        → empréstimo 1 (Ana)
                                                                       (1, 3, 2),  -- Refactoring → empréstimo 2 (Ana, aberto)
                                                                       (2, 1, 3),  -- Clean Code  → empréstimo 3 (Bruno) – 2 exemplares
                                                                       (1, 4, 3),  -- Pragmatic   → empréstimo 3 (Bruno)
                                                                       (1, 5, 4),  -- Design Patterns → empréstimo 4 (Carla)
                                                                       (1, 6, 4);  -- Spring in Action → empréstimo 4 (Carla)