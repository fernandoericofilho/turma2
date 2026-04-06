CREATE TABLE produto (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco NUMERIC(10,2) NOT NULL
);

CREATE TABLE produto_pedido (
    pedido_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    preco NUMERIC(10,2) NOT NULL,

    PRIMARY KEY (pedido_id, produto_id),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

INSERT INTO produto (nome, preco) VALUES ('arroz', 8.50);
INSERT INTO produto (nome, preco) VALUES ('açucar', 10.20);
INSERT INTO produto (nome, preco) VALUES ('feijão', 12.00);
INSERT INTO produto (nome, preco) VALUES ('rapadura', 5.50);

INSERT INTO produto_pedido (pedido_id, produto_id, preco) VALUES (1, 1, 8.80);
INSERT INTO produto_pedido (pedido_id, produto_id, preco) VALUES (1, 2, 9.50);