CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE pedidos_produtos (
    id_pedido INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_pedido, id_produto),
    CONSTRAINT fk_pedidos_produtos_pedido
        FOREIGN KEY (id_pedido)
        REFERENCES pedidos(id),
    CONSTRAINT fk_pedidos_produtos_produto
        FOREIGN KEY (id_produto)
        REFERENCES produtos(id)
);

INSERT INTO produtos (nome, preco) VALUES
    ('Teclado Mecanico', 250.00),
    ('Mouse Gamer', 150.00),
    ('Monitor 24', 899.90);

INSERT INTO pedidos_produtos (id_pedido, id_produto, quantidade, preco_unitario) VALUES
    (1, 1, 1, 250.00),
    (1, 2, 2, 150.00),
    (2, 3, 1, 899.90);

SELECT
    pp.id_pedido,
    p.nome AS produto,
    pp.quantidade,
    pp.preco_unitario
FROM pedidos_produtos pp
INNER JOIN produtos p ON p.id = pp.id_produto;
