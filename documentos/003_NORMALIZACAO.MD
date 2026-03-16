# 📚 Normalização de Banco de Dados
## 1ª, 2ª e 3ª Forma Normal (1FN, 2FN e 3FN)

A **normalização de banco de dados** é um processo usado para **organizar os dados em tabelas menores e relacionadas**, evitando problemas como:

- duplicação de dados
- inconsistência de informações
- dificuldade de manutenção
- atualizações incorretas

O objetivo é **garantir integridade e eficiência no banco de dados**.

As três primeiras formas normais são as mais utilizadas no desenvolvimento de sistemas.

---

# 1️⃣ Primeira Forma Normal (1FN)

Uma tabela está na **1ª Forma Normal** quando:

- Cada coluna possui **valores atômicos (apenas um valor por célula)**
- Não existem **listas ou múltiplos valores na mesma coluna**
- Cada registro pode ser identificado por uma **chave primária**

## ❌ Exemplo incorreto

| cliente_id | nome | telefones |
|---|---|---|
| 1 | João | 9999-1111, 9888-2222 |

Problema:

O campo **telefones possui mais de um valor**.

---

## ✅ Forma correta

### Tabela Cliente

| cliente_id | nome |
|---|---|
| 1 | João |

### Tabela Telefone

| telefone_id | cliente_id | telefone |
|---|---|---|
| 1 | 1 | 9999-1111 |
| 2 | 1 | 9888-2222 |

---

# 2️⃣ Segunda Forma Normal (2FN)

Para uma tabela estar na **2ª Forma Normal**, ela precisa:

- Estar na **1FN**
- Todos os atributos devem depender **da chave primária completa**

Isso é importante quando existe **chave composta**.

## ❌ Exemplo incorreto

| pedido_id | produto_id | produto_nome |
|---|---|---|
| 1 | 10 | Notebook |

Aqui temos uma chave composta:


(pedido_id, produto_id)


Mas:


produto_nome depende apenas de produto_id


Isso gera redundância.

---

## ✅ Forma correta

### Tabela Produto

| produto_id | produto_nome |
|---|---|
| 10 | Notebook |

### Tabela Pedido_Produto

| pedido_id | produto_id |
|---|---|
| 1 | 10 |

---

# 3️⃣ Terceira Forma Normal (3FN)

Para estar na **3ª Forma Normal**, a tabela precisa:

- Estar na **2FN**
- Não possuir **dependências transitivas**

Dependência transitiva ocorre quando:


A → B
B → C


Ou seja:

**C depende indiretamente de A**.

---

## ❌ Exemplo incorreto

| funcionario_id | nome | departamento | gerente_departamento |
|---|---|---|---|
| 1 | João | TI | Carlos |

Problema:


departamento → gerente_departamento


Ou seja, o gerente depende do **departamento**, não do funcionário.

---

## ✅ Forma correta

### Tabela Funcionário

| funcionario_id | nome | departamento_id |
|---|---|---|

### Tabela Departamento

| departamento_id | departamento_nome | gerente |
|---|---|---|

---

# 🎯 Benefícios da Normalização

Aplicar normalização ajuda a:

- reduzir redundância de dados
- evitar inconsistências
- facilitar manutenção
- melhorar integridade do banco

---

# 📚 Próximos Conceitos para Estudar

Depois de entender normalização, é importante estudar também:

## 📌 Cardinalidade

Define **quantos registros de uma entidade podem se relacionar com outra**.

Exemplos:

- **1:1** → Pessoa → Passaporte
- **1:N** → Cliente → Pedidos
- **N:N** → Alunos → Disciplinas

---

## 📌 DER (Diagrama Entidade-Relacionamento)

O **DER** é a representação visual da estrutura do banco de dados.

Ele mostra:

- Entidades
- Relacionamentos
- Cardinalidade
- Atributos
- Chaves primárias e estrangeiras
