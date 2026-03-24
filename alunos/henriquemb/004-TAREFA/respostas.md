# Exercício 1 — Cliente e Pedido
### 1. Quais tabelas existem?
Cliente e Pedido

### 2. Qual a cardinalidade?
Cardinalidade: 1:N;

Um cliente pode ter vários pedidos, mas um pedido só pode ter um cliente.

### 3. Defina PK e FK
* PK: Cliente (id), Pedido (id)
* FK: Pedido (cliente_id)

### 4. Faça um INNER JOIN
```sql
SELECT
    pedido.id AS pedido_id,
    cliente.id AS cliente_id
FROM pedido
INNER JOIN cliente ON
    cliente.id = pedido.cliente_id
```

![img_001-exercicio_pedido-cliente.png](./img/001-exercicio_pedido-cliente.png)

# Exercício 2 — Pedido e Produto
### 1. Qual o tipo de relacionamento? 
Relacionamento: N:N;

### 2. Qual tabela intermediária criar?
Tabela: pedido_produto
### 3. Defina chave composta
Chave composta: pedido_produto (pedido_id, produto_id)

### 4. Monte as tabelas
```sql
CREATE TABLE produto (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE pedido_produto (
    pedido_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (pedido_id, produto_id),

    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
```

### Schema exercício 1 e 2
![img_schema_exercicios-faceis.png](./img/schema_exercicios-faceis.png)


### Dúvidas:
1. Qual impacto real caso na tabela ``pedido_produto`` ao invés de usar ``PRIMARY KEY (pedido_id, produto_id)`` eu usar ``PRIMARY KEY (id)`` e criar um campo ``id`` auto-incremento com uma ``UNIQUE (pedido_id, produto_id)``?

# Exercício 3 — Funcionário e Departamento
### 1. Qual a cardinalidade?
Cardinalidade: N:1;

### 2. Onde fica a FK?
FK: funcionário (departamento_id)

### 3. Modele as tabelas
![img_schema_exercicios-intermediarios.png](./img/schema_exercicios-intermediarios.png)
![img_003-exercicio_funcionario-departamento.png](./img/003-exercicio_funcionario-departamento.png)

# Exercício 4 — Índices
### 1. Em quais colunas você criaria índices?
Criaria um índice na coluna ``cliente_id``

### 2. Por quê?
É a coluna que realiza o vínculo com o cliente, e é a coluna que será utilizada em consultas e joins

# Exercício 5 — Sistema Escolar
### 1. Identifique relacionamentos
* aluno - aluno_disciplina: 1:N
* disciplina - aluno_disciplina: 1:N
* professor - professor_disciplina: 1:N
* disciplina - professor_disciplina: 1:N

### 2. Existe N:N?
* aluno - disciplina: N:N
* professor - disciplina: N:N

### 3. Quais tabelas criar?
aluno, disciplina, professor, aluno_disciplina, professor_disciplina

### 4. Monte o DER (imagem)
![img_schema_exercicios-dificeis.png](./img/schema_exercicios-dificeis.png)
![img_005-exercicio_alunos-disciplina.png](./img/005-exercicio_alunos-disciplina.png)

# Exercício 6 — Joins
### 1. Faça: INNER JOIN e LEFT JOIN
```sql
-- INNER JOIN
SELECT
    cliente.id AS cliente_id,
    cliente.nome AS cliente_nome,
    pedido.id AS pedido_id
FROM cliente
         INNER JOIN pedido ON
    cliente.id = pedido.cliente_id

-- LEFT JOIN
SELECT
    cliente.id AS cliente_id,
    cliente.nome AS cliente_nome,
    pedido.id AS pedido_id
FROM cliente
LEFT JOIN pedido ON
    cliente.id = pedido.cliente_id
```

### 2. Explique a diferença
O ``INNER JOIN`` retorna apenas os clientes que possuem pedidos.
![img_006-exercicio_cliente_com_pedido.png](./img/006-exercicio_cliente_com_pedido.png)

O ``LEFT JOIN`` retorna todos os clientes independente se ele possui pedidos ou não.
![img_006-exercicio_cliente_pedido.png](./img/006-exercicio_cliente_pedido.png)