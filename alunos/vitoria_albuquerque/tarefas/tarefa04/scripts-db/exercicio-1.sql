CREATE TABLE cliente (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE pedido (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

INSERT INTO cliente (nome) VALUES ('Vitória');
INSERT INTO cliente (nome) VALUES ('Lorena');
INSERT INTO cliente (nome) VALUES ('Pedro');

INSERT INTO pedido (cliente_id) VALUES (1);
INSERT INTO pedido (cliente_id) VALUES (1);
INSERT INTO pedido (cliente_id) VALUES (2);

SELECT c.nome, p.cliente_id
FROM cliente c
INNER JOIN pedido p ON c.id = p.cliente_id