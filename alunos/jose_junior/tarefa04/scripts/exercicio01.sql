-- Criando as tabelas Clientes e Pedidos

CREATE TABLE Clientes(
    id SERIAL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Pedidos(
    id SERIAL,
    id_cliente INT NOT NULL,
    data_pedido DATE NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_pedidos_clientes
        FOREIGN KEY(id_cliente)
        REFERENCES Clientes(id)
);

-- Inserindo dados nas tabelas Clientes e Pedidos

INSERT INTO Clientes (nome)
VALUES
    ('João'),
    ('Maria'),
    ('Pedro'),
    ('Ana');

INSERT INTO Pedidos(id_cliente, data_pedido)
VALUES
    (1, '2026-02-25'),
    (1, '2026-02-25'),
    (2, '2026-03-01'),
    (2, '2026-03-02'),
    (3, '2026-03-15');

-- Fazendo uma consulta utilizando INNER JOIN no SELECT para retornar os pedidos de cada cliente.

SELECT p.id AS "pedido_id", c.id AS "cliente_id", c.nome, p.data_pedido FROM clientes c
INNER JOIN pedidos p
ON c.id = p.id_cliente;