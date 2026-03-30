-- Fazendo uma consulta utilizando INNER JOIN no SELECT para retornar os pedidos de cada cliente.

SELECT p.id AS pedido_id, c.id AS cliente_id, c.nome, p.data_pedido
FROM clientes c
INNER JOIN pedidos p
ON c.id = p.id_cliente;

-- Fazendo uma consulta utilizando LEFT JOIN no SELECT para retornar os pedidos de cada cliente.

SELECT p.id AS pedido_id, c.id AS cliente_id, c.nome, p.data_pedido
FROM clientes c
LEFT JOIN pedidos p
ON c.id = p.id_cliente;