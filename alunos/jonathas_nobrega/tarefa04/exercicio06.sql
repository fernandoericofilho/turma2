INSERT INTO clientes (nome, email) VALUES
    ('Diego Sem Pedido', 'diego.sem.pedido@email.com');

INSERT INTO pedidos (id_cliente, data_pedido, valor_total) VALUES
    (1, '2026-04-07', 210.00);

SELECT
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    p.id AS pedido_id,
    p.data_pedido
FROM clientes c
INNER JOIN pedidos p ON p.id_cliente = c.id;

SELECT
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    p.id AS pedido_id,
    p.data_pedido
FROM clientes c
LEFT JOIN pedidos p ON p.id_cliente = c.id;
