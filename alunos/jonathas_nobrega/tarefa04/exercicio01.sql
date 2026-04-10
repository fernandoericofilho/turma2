CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) UNIQUE
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL,
    data_pedido DATE NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_pedidos_clientes
        FOREIGN KEY (id_cliente)
        REFERENCES clientes(id)
);

INSERT INTO clientes (nome, email) VALUES
    ('Ana Silva', 'ana.silva@email.com'),
    ('Bruno Lima', 'bruno.lima@email.com'),
    ('Carla Souza', 'carla.souza@email.com');

INSERT INTO pedidos (id_cliente, data_pedido, valor_total) VALUES
    (1, '2026-04-01', 120.50),
    (1, '2026-04-03', 89.90),
    (2, '2026-04-05', 45.00);

SELECT
    p.id AS pedido_id,
    c.id AS cliente_id,
    c.nome,
    p.data_pedido,
    p.valor_total
FROM pedidos p
INNER JOIN clientes c ON c.id = p.id_cliente;
