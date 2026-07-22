# Exercícios de Normalização de Banco de Dados

## 🟢 Exercícios Básicos

### Exercício 1 — Telefones de Cliente

**Tabela inicial:**

| pedido_id | cliente | produtos    |
|-----------|---------|-------------|
| 1         | João    | arroz, feijão |
| 2         | Maria   | leite       |

**Perguntas:**

**Qual problema de normalização existe?**
Não está correto. Há violação da tabela telefone. Ligado pelo cliente_id.

**Como ficaria a estrutura após aplicar 1FN?**

**Correção:**

**Tabela cliente**

| cliente_id (PK) | nome |
|-----------------|------|
| 1               | João |
| 2               | Maria|

**Tabela telefone**

| telefone_id (PK) | cliente_id (FK) | numero     |
|------------------|-----------------|------------|
| 1                | 1               | 9999-1111  |
| 2                | 1               | 9888-2222  |
| 3                | 2               | 9777-3333  |

---

### Exercício 2 — Pedido com vários produtos

**Tabela inicial:**

| pedido_id | cliente | produtos    |
|-----------|---------|-------------|
| 1         | João    | arroz, feijão |
| 2         | Maria   | leite       |

**Perguntas:**

**Qual problema de normalização existe?**
A tabela original tem uma lista de produtos separada por vírgula na coluna produtos. Isso viola a 1FN.

**Como ficaria a estrutura após aplicar 1FN?**

**Correção:**

**Tabela pedido**

| pedido_id (PK) | cliente |
|----------------|---------|
| 1              | João    |
| 2              | Maria   |

**Tabela item_pedido** (tabela auxiliar para os produtos de cada pedido)

| item_id (PK) | pedido_id (FK) | produto_nome |
|--------------|----------------|--------------|
| 1            | 1              | arroz        |
| 2            | 1              | feijão       |
| 3            | 2              | leite        |

---

### Exercício 3 — Curso e alunos

**Tabela inicial:**

| curso_id | curso_nome | alunos           |
|----------|------------|------------------|
| 1        | Java       | Ana, Carlos, Pedro |

**Perguntas:**

**Qual regra da 1FN está sendo violada?**
A coluna alunos tem uma lista com vírgula (Ana, Carlos, Pedro). Isso viola a 1FN. A relação correta é: um curso tem muitos alunos.

**Como ficaria a modelagem correta?**

**Correção:**

**Tabela curso**

| curso_id (PK) | curso_nome |
|---------------|------------|
| 1             | Java       |

**Tabela aluno**

| aluno_id (PK) | aluno_nome |
|---------------|------------|
| 1             | Ana        |
| 2             | Carlos     |
| 3             | Pedro      |

**Tabela matricula** (tabela de relacionamento)

| matricula_id (PK) | curso_id (FK) | aluno_id (FK) |
|-------------------|---------------|---------------|
| 1                 | 1             | 1             |
| 2                 | 1             | 2             |
| 3                 | 1             | 3             |

---

## 🟡 Exercícios Intermediários

### Exercício 4 — Funcionários e Departamento

**Tabela inicial:**

| funcionario_id | nome  | departamento | gerente_departamento |
|----------------|-------|--------------|----------------------|
| 1              | João  | TI           | Carlos               |
| 2              | Ana   | TI           | Carlos               |
| 3              | Pedro | RH           | Maria                |

**Perguntas:**

**Existe dependência transitiva?**
Sim, para saber o departamento é necessário uma relação com seu gerente responsável, entretanto na tabela ele está atrelado ao funcionario_id.

**A tabela está na 3FN?**
Não, ela tem dependência transitiva, portanto ela não está na 3FN.

**Como separar corretamente?**

**Tabela funcionarios**

| funcionario_id | nome  | departamento_id |
|----------------|-------|-----------------|
| 1              | João  | 1               |
| 2              | Ana   | 1               |
| 3              | Pedro | 2               |

**Tabela departamentos**

| departamento_id | nome_departamento | gerente_departamento |
|-----------------|-------------------|----------------------|
| 1               | TI                | Carlos               |
| 2               | RH                | Maria                |

---

### Exercício 5 — Produtos e Categoria

**Tabela inicial:**

| produto_id | produto_nome | categoria_id | categoria_nome   |
|------------|--------------|--------------|------------------|
| 1          | Notebook     | 10           | Informática      |
| 2          | Mouse        | 10           | Informática      |
| 3          | Geladeira    | 20           | Eletrodomésticos |

**Perguntas:**

**Qual dependência funcional existe?**
Existe dependência nas categorias e na repetição do nome. Existe a dependência no categoria_id e categoria_nome. Informática está repetida para Notebook e Mouse. Se eu precisar mudar "Informática" para "Tecnologia", tenho que atualizar 2 linhas. Se eu esquecer uma, o banco fica inconsistente.

**Como ficaria a tabela na 3FN?**
Seria feito duas novas tabelas: a primeira interligando apenas categoria_id e categoria_nome, e a segunda com produto_id, produto_nome e a categoria_id.

**Tabela categorias**

| categoria_id | categoria_nome   |
|--------------|------------------|
| 10           | Informática      |
| 20           | Eletrodomésticos |

**Tabela produtos**

| produto_id | produto_nome | categoria_id |
|------------|--------------|--------------|
| 1          | Notebook     | 10           |
| 2          | Mouse        | 10           |
| 3          | Geladeira    | 20           |

---

### Exercício 6 — Matrícula de alunos

**Tabela inicial:**

| aluno_id | aluno_nome | curso_id | curso_nome   |
|----------|------------|----------|--------------|
| 1        | Ana        | 101      | Engenharia   |
| 2        | Pedro      | 101      | Engenharia   |
| 3        | Carlos     | 102      | Direito      |

**Perguntas:**

**Quais atributos dependem de quais?**

- `aluno_id → aluno_nome` (o nome depende do aluno)
- `curso_id → curso_nome` (o nome do curso depende do ID do curso)

**Problema na tabela original:**
- `curso_nome` está repetido para Engenharia (2 vezes)
- `curso_nome` depende de `curso_id`, não de `aluno_id`
- Isso viola a 2FN/3FN (dependência parcial/transitiva)

**Separe as tabelas corretamente.**

**Tabela cursos**

| curso_id | curso_nome   |
|----------|--------------|
| 101      | Engenharia   |
| 102      | Direito      |

**Tabela alunos**

| aluno_id | aluno_nome | curso_id |
|----------|------------|----------|
| 1        | Ana        | 101      |
| 2        | Pedro      | 101      |
| 3        | Carlos     | 102      |

---

## 🔴 Exercícios Difíceis

### Exercício 7 — Pedido completo

**Tabela inicial:**

| pedido_id | cliente_nome | cliente_cidade | produto_nome | preco |
|-----------|--------------|----------------|--------------|-------|
| 1         | João         | Recife         | Notebook     | 4000  |
| 1         | João         | Recife         | Mouse        | 50    |
| 2         | Maria        | São Paulo      | Teclado      | 120   |

**Perguntas:**

**Identifique as dependências funcionais:**
- `pedido_id → cliente_nome, cliente_cidade`
- `produto_nome → preco`
- `(pedido_id, produto_nome)` → chave composta da tabela original

**Normalize até 3FN**

**Tabela clientes**

| cliente_id (PK) | cliente_nome | cliente_cidade |
|-----------------|--------------|----------------|
| 1               | João         | Recife         |
| 2               | Maria        | São Paulo      |

**Tabela produtos**

| produto_id (PK) | produto_nome | preco |
|-----------------|--------------|-------|
| 1               | Notebook     | 4000  |
| 2               | Mouse        | 50    |
| 3               | Teclado      | 120   |

**Tabela pedidos**

| pedido_id (PK) | cliente_id (FK) |
|----------------|-----------------|
| 1              | 1               |
| 2              | 2               |

**Tabela item_pedido** (para resolver o relacionamento N:N)

| item_pedido_id (PK) | pedido_id (FK) | produto_id (FK) |
|---------------------|----------------|-----------------|
| 1                   | 1              | 1               |
| 2                   | 1              | 2               |
| 3                   | 2              | 3               |

---

### Exercício 8 — Venda e vendedor

**Tabela inicial:**

| venda_id | vendedor_nome | loja_nome    | loja_cidade |
|----------|---------------|--------------|-------------|
| 1        | Carlos        | Loja Centro  | Recife      |
| 2        | Carlos        | Loja Centro  | Recife      |
| 3        | Ana           | Loja Norte   | São Paulo   |

**Perguntas:**

**Existe dependência transitiva?**
Uma dependência transitiva acontece quando um atributo que não é chave depende de outro atributo que também não é chave.

Olhando para os dados, podemos notar as seguintes dependências funcionais:
- `venda_id → vendedor_nome, loja_nome` (A venda determina quem vendeu e em qual loja)
- `loja_nome → loja_cidade` (A cidade depende exclusivamente da loja, e não da venda em si)

Como `venda_id` define `loja_nome`, e `loja_nome` define `loja_cidade`, temos uma dependência transitiva: `loja_cidade` depende de `venda_id` através de `loja_nome`. Isso é o que a 3FN proíbe.

⚠️ **Nota sobre o Vendedor:** O exercício não deixou explícito se um vendedor pode trabalhar em mais de uma loja. Mas, olhando para os dados, o Carlos está sempre na Loja Centro. Se assumirmos que cada vendedor pertence a apenas uma loja, existe outra dependência funcional: `vendedor_nome → loja_nome`.

**Como dividir as tabelas?**

**Tabela lojas**

| loja_id (PK) | loja_nome    | loja_cidade |
|--------------|--------------|-------------|
| 1            | Loja Centro  | Recife      |
| 2            | Loja Norte   | São Paulo   |

**Tabela vendedores**

| vendedor_id (PK) | vendedor_nome | loja_id (FK) |
|------------------|---------------|--------------|
| 1                | Carlos        | 1            |
| 2                | Ana           | 2            |

**Tabela vendas**

| venda_id (PK) | vendedor_id (FK) |
|---------------|------------------|
| 1             | 1                |
| 2             | 1                |
| 3             | 2                |

---

### Exercício 9 — Sistema de universidade

**Tabela inicial:**

| aluno_id | aluno_nome | disciplina     | professor |
|----------|------------|----------------|-----------|
| 1        | Ana        | Banco de Dados | Marcos    |
| 2        | Pedro      | Banco de Dados | Marcos    |
| 3        | Ana        | Redes          | Carla     |

**Perguntas:**

**Existe relação N:N?**
- **Aluno e Disciplina:** É N:N (Muitos para Muitos). Um aluno cursa várias disciplinas, e uma disciplina tem vários alunos.
- **Disciplina e Professor:** Olhando os dados, cada disciplina tem apenas um professor específico (Banco de Dados é sempre o Marcos). Portanto, a relação entre Disciplina e Professor é 1:N (Um professor pode lecionar várias disciplinas, mas cada disciplina tem apenas um professor).

**Quais tabelas devem existir?**

**Tabela alunos**

| aluno_id (PK) | aluno_nome |
|---------------|------------|
| 1             | Ana        |
| 2             | Pedro      |

**Tabela professores**

| professor_id (PK) | professor |
|-------------------|-----------|
| 1                 | Marcos    |
| 2                 | Carla     |

**Tabela disciplinas**

| disciplina_id (PK) | disciplina_nome | professor_id (FK) |
|--------------------|-----------------|-------------------|
| 1                  | Banco de Dados  | 1                 |
| 2                  | Redes           | 2                 |

**Tabela matricula** (para resolver o N:N entre alunos e disciplinas)

| matricula_id (PK) | aluno_id (FK) | disciplina_id (FK) |
|-------------------|---------------|--------------------|
| 1                 | 1             | 1                  |
| 2                 | 2             | 1                  |
| 3                 | 1             | 2                  |

---

### Exercício 10 — Sistema de e-commerce

**Tabela inicial:**

| pedido_id | cliente_nome | cliente_email        | produto_nome | categoria        |
|-----------|--------------|----------------------|--------------|------------------|
| 1         | João         | joao@email.com       | Notebook     | Informática      |
| 1         | João         | joao@email.com       | Mouse        | Informática      |
| 2         | Ana          | ana@email.com        | Geladeira    | Eletrodomésticos |

**Perguntas:**

**Identifique dependências funcionais:**
- `pedido_id → cliente_nome, cliente_email` (O número do pedido determina quem é o cliente)
- `cliente_email → cliente_nome` (O e-mail determina o nome do cliente)
- `produto_nome → categoria` (O produto determina a sua categoria. Um "Notebook" sempre será da categoria "Informática", independentemente de haver um pedido ou não)

**Normalize até 3FN.**

Para eliminar as redundâncias (como repetir o e-mail do João e a categoria "Informática" várias vezes), precisamos separar o banco de dados. Como um pedido pode ter vários produtos, e um produto pode estar em vários pedidos, temos aqui uma relação Muitos-para-Muitos (N:N) entre Pedidos e Produtos. Para o resultado final, precisamos criar 5 tabelas para que fique 100% correto na 3FN.

**Quais seriam as tabelas finais?**

**Tabela clientes**

| cliente_id (PK) | cliente_nome | cliente_email        |
|-----------------|--------------|----------------------|
| 1               | João         | joao@email.com       |
| 2               | Ana          | ana@email.com        |

**Tabela categorias**

| categoria_id (PK) | categoria_nome   |
|-------------------|------------------|
| 1                 | Informática      |
| 2                 | Eletrodomésticos |

**Tabela produtos**

| produto_id (PK) | produto_nome | categoria_id (FK) |
|-----------------|--------------|-------------------|
| 1               | Notebook     | 1                 |
| 2               | Mouse        | 1                 |
| 3               | Geladeira    | 2                 |

**Tabela pedidos**

| pedido_id (PK) | cliente_id (FK) |
|----------------|-----------------|
| 1              | 1               |
| 2              | 2               |

**Tabela item_pedido** (para resolver o N:N entre pedidos e produtos)

| item_pedido_id (PK) | pedido_id (FK) | produto_id (FK) |
|---------------------|----------------|-----------------|
| 1                   | 1              | 1               |
| 2                   | 1              | 2               |
| 3                   | 2              | 3               |
