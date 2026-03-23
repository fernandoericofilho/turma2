/*
 * Seleciona o schema/banco de dados
 */
\c turma2;

/*
 * Consulta os dados
 */
SELECT
    cliente.id AS cliente_id,
    cliente.nome AS cliente_nome,
    pedido.id AS pedido_id
FROM cliente
INNER JOIN pedido ON
    cliente.id = pedido.cliente_id

SELECT
    cliente.id AS cliente_id,
    cliente.nome AS cliente_nome,
    pedido.id AS pedido_id
FROM cliente
LEFT JOIN pedido ON
    cliente.id = pedido.cliente_id