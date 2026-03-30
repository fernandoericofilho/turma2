-- Criando a tabela Produtos e a tabela intermediária/associativa entre Produtos e Pedidos (Pedidos_Produtos)

CREATE TABLE Produtos(
    id SERIAL,
    nome TEXT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Pedidos_Produtos(
    id_produto INT NOT NULL,
    id_pedido INT NOT NULL,
    preco_compra DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (id_produto, id_pedido),
    CONSTRAINT fk_pedidos_produtos_produto
        FOREIGN KEY (id_produto)
        REFERENCES Produtos(id),
    CONSTRAINT fk_pedidos_produtos_pedido
        FOREIGN KEY (id_pedido)
        REFERENCES Pedidos(id)
);