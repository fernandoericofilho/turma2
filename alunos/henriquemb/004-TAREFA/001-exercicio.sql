/*
 * Cria as tabelas do Exercício 1
 */
CREATE TABLE cliente (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE pedido (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

/*
 * Insere dados de exemplo
 */
INSERT INTO cliente (nome) VALUES ('João');
INSERT INTO cliente (nome) VALUES ('Maria');
INSERT INTO cliente (nome) VALUES ('Pedro');

INSERT INTO pedido (cliente_id) VALUES (1);
INSERT INTO pedido (cliente_id) VALUES (1);
INSERT INTO pedido (cliente_id) VALUES (2);


/*
 * Consulta os pedidos existentes
 */
SELECT
    pedido.id AS pedido_id,
    cliente.id AS cliente_id
FROM pedido
INNER JOIN cliente ON
    cliente.id = pedido.cliente_id