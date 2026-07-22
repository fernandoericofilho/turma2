# Tarefa 04

## 📌 Instruções

Para cada exercício:

- Modele as tabelas corretamente.
- Defina PK e FK.
- Identifique a cardinalidade.
- (Opcional) Desenhe o DER.
- Escreva exemplos de JOIN.

---

# 🟢 Exercício 1 — Cliente e Pedido

## Cenário

Um cliente pode fazer vários pedidos.

## Perguntas

### Quais tabelas existem?

Para este cenário, precisamos de duas tabelas principais:

- **Clientes:** para armazenar os dados cadastrais de quem compra.
- **Pedidos:** para armazenar os dados de cada compra realizada.

### Qual a cardinalidade?

A cardinalidade entre **Cliente** e **Pedido** é **1:N (Um para Muitos).**

**Explicação:** Um cliente pode realizar vários (N) pedidos ao longo do tempo. Porém, cada pedido específico pertence a apenas um (1) cliente.

### Defina PK e FK

Para conectar o pedido ao cliente que o fez, precisamos passar a **Chave Primária** de **Clientes** para dentro da tabela **Pedidos** como uma **Chave Estrangeira**.

#### Tabela Clientes

- `cliente_id` ➜ **PK (Chave Primária)**
- `nome`
- `email`

#### Tabela Pedidos

- `pedido_id` ➜ **PK (Chave Primária)**
- `data_pedido`
- `valor_total`
- `cliente_id` ➜ **FK (Chave Estrangeira)** (Aponta para `cliente_id` da tabela **Clientes**)

### Faça um INNER JOIN

```sql
SELECT
    Clientes.nome AS Nome_Do_Cliente,
    Pedidos.pedido_id AS Numero_Do_Pedido,
    Pedidos.data_pedido AS Data_Da_Compra,
    Pedidos.valor_total AS Valor
FROM Pedidos
INNER JOIN Clientes
    ON Pedidos.cliente_id = Clientes.cliente_id;
```

---

# 🟢 Exercício 2 — Pedido e Produto

## Cenário

- Um pedido pode ter vários produtos.
- Um produto pode estar em vários pedidos.

## Perguntas

### Qual o tipo de relacionamento?

Relacionamento **N:N (Muitos para Muitos).**

### Qual tabela intermediária criar?

`Item_Pedido`

### Defina chave composta

Chave composta:

```text
(id_pedido, id_produto)
```

### Monte as tabelas

```sql
CREATE TABLE Produto (
    id_produto INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT DEFAULT 0
);

CREATE TABLE Pedido (
    id_pedido INT PRIMARY KEY,
    data_pedido DATE NOT NULL,
    valor_total DECIMAL(10,2),
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE Item_Pedido (
    id_pedido INT,
    id_produto INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,

    PRIMARY KEY (id_pedido, id_produto),

    FOREIGN KEY (id_pedido)
        REFERENCES Pedido(id_pedido),

    FOREIGN KEY (id_produto)
        REFERENCES Produto(id_produto)
);
```

---

# 🟡 Exercício 3 — Funcionário e Departamento

## Cenário

- Um funcionário pertence a um departamento.
- Um departamento possui vários funcionários.

## Perguntas

### Qual a cardinalidade?

**1:N (Um para Muitos).**

### Onde fica a FK?

`departamento_id`

### Modele as tabelas

```sql
CREATE TABLE Departamento (
    departamento_id INT PRIMARY KEY,
    departamento_name VARCHAR(100) NOT NULL
);

CREATE TABLE Funcionarios (
    id_funcionario INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    departamento_id INT,

    FOREIGN KEY (departamento_id)
        REFERENCES Departamento(departamento_id)
);
```

---

# 🟡 Exercício 4 — Índices

## Cenário

Tabela de pedidos com milhares de registros.

## Perguntas

### Em quais colunas você criaria índices?

Em um banco de dados geralmente criamos índices nas colunas utilizadas para pesquisas e filtros, aumentando a velocidade das consultas. Normalmente essas colunas são utilizadas em cláusulas `WHERE` e também em operações de `JOIN`.

As principais colunas seriam:

- `pedido_id`
- `cliente_id`
- `data_pedido`
- `status_pedido`

### Por quê?

Porque, se o sistema de e-commerce precisar listar os pedidos do cliente **João**, em vez de o banco percorrer milhares ou milhões de registros procurando manualmente, o índice informa rapidamente onde estão os pedidos desse cliente.

Assim, a consulta pode passar de alguns segundos para apenas alguns milissegundos.

---

# 🔴 Exercício 5 — Sistema Escolar

## Cenário

- Alunos fazem disciplinas.
- Professores ministram disciplinas.

## Perguntas

### Identifique os relacionamentos

- Alunos cursam disciplinas.
- Disciplinas possuem alunos.
- Professores ministram disciplinas.

### Existe relacionamento N:N?

**Sim.**

Uma disciplina pode ser cursada por vários alunos, e um aluno pode cursar várias disciplinas.

### Quais tabelas criar?

É necessário criar uma tabela intermediária para representar a matrícula dos alunos nas disciplinas.

Exemplo:

- Alunos
- Professores
- Disciplinas
- Matrículas (ou Alunos_Disciplinas)

### Monte o DER

*(Inserir imagem do DER.)*

---

# 🔴 Exercício 6 — Joins

## Dadas as tabelas

- Clientes
- Pedidos

## Perguntas

### Faça um INNER JOIN

```sql
SELECT
    Clientes.nome,
    Pedidos.pedido_id,
    Pedidos.data_pedido
FROM Clientes
INNER JOIN Pedidos
    ON Clientes.cliente_id = Pedidos.cliente_id;
```

### Faça um LEFT JOIN

```sql
SELECT
    Clientes.nome,
    Pedidos.pedido_id,
    Pedidos.data_pedido
FROM Clientes
LEFT JOIN Pedidos
    ON Clientes.cliente_id = Pedidos.cliente_id;
```

### Explique a diferença

**INNER JOIN**

Retorna apenas os registros que possuem correspondência nas duas tabelas. Ou seja, serão exibidos somente os clientes que possuem pedidos cadastrados.

**LEFT JOIN**

Retorna todos os registros da tabela da esquerda (`Clientes`), mesmo que não exista um pedido correspondente. Caso um cliente não tenha realizado pedidos, as colunas da tabela `Pedidos` aparecerão com valor `NULL`.