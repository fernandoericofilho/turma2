# 📚 Modelagem de Banco de Dados
## Chaves, Índices, Cardinalidade, DER e Joins

Após aprender normalização, o próximo passo é entender como os dados se conectam e são utilizados na prática.

Essa etapa aborda conceitos fundamentais usados no dia a dia de qualquer desenvolvedor backend.

---

# 🔑 Chaves em Banco de Dados

## 📌 Chave Primária (Primary Key - PK)

- Identifica **unicamente** um registro
- Não pode ser nula
- Não pode se repetir

### Exemplo:

| cliente_id (PK) | nome |
|---|---|
| 1 | João |

---

## 📌 Chave Estrangeira (Foreign Key - FK)

- Cria relacionamento entre tabelas
- Aponta para a PK de outra tabela

### Exemplo:

| pedido_id | cliente_id (FK) |
|---|---|
| 1 | 1 |

---

## 📌 Chave Composta

- Formada por **mais de uma coluna**
- Muito usada em tabelas de relacionamento (N:N)

### Exemplo:

| pedido_id | produto_id |
|---|---|

---

# ⚡ Índices (Indexes)

Índices são usados para **melhorar a performance de consultas**.

Eles funcionam como um índice de livro.

## 📌 Quando usar?

- Colunas muito buscadas (WHERE)
- Colunas usadas em JOIN
- Colunas usadas em ORDER BY

## ⚠️ Atenção

- Muitos índices podem prejudicar INSERT/UPDATE
- Use com equilíbrio

---

# 🔗 Cardinalidade

Define como as entidades se relacionam.

## Tipos:

### 1️⃣ 1:1 (Um para Um)

- Ex: Pessoa → Passaporte

### 2️⃣ 1:N (Um para Muitos)

- Ex: Cliente → Pedidos

### 3️⃣ N:N (Muitos para Muitos)

- Ex: Aluno → Curso

Nesse caso, criamos uma tabela intermediária:

Aluno_Curso

---

# 🧩 DER (Diagrama Entidade-Relacionamento)

O DER representa visualmente o banco de dados.

## Elementos:

- Entidades (tabelas)
- Atributos (colunas)
- Relacionamentos
- Cardinalidade
- Chaves (PK e FK)

---

# 🔄 Joins (Junções)

Joins são usados para buscar dados de múltiplas tabelas.

---

## 🔹 INNER JOIN

Retorna apenas registros que possuem correspondência.

```sql
SELECT *
FROM pedidos p
INNER JOIN clientes c ON p.cliente_id = c.cliente_id;
🔹 LEFT JOIN

Retorna tudo da tabela da esquerda + correspondências

SELECT *
FROM clientes c
LEFT JOIN pedidos p ON c.cliente_id = p.cliente_id;
🔹 RIGHT JOIN

Retorna tudo da tabela da direita

🔹 FULL JOIN

Retorna tudo de ambas as tabelas

🎯 Resumo

Você aprendeu:

Tipos de chaves
Uso de índices
Cardinalidade
Modelagem com DER
Joins para consulta de dados

Esses conceitos são fundamentais para:

APIs
Sistemas backend
Engenharia de dados
Performance de banco
