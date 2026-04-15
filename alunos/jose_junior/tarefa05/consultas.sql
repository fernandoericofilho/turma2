-- Listar todos os livros emprestados.

SELECT titulo, autor, quantidade, data_emprestimo, data_devolucao FROM livros l JOIN livros_emprestimos le ON l.id = le.livro_id
JOIN emprestimos emp ON emp.id = le.emprestimo_id;

-- Contar quantos empréstimos cada livro teve.

SELECT COUNT(livro_id) AS vezes_emprestado, titulo FROM livros liv JOIN
livros_emprestimos lemp ON liv.id = lemp.livro_id
GROUP BY liv.id;