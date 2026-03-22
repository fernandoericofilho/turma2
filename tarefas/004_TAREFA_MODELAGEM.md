# 🧪 Tarefa 04 — Modelagem de Banco de Dados

## 🎯 Objetivo

Aplicar conceitos de:

- Chaves primárias e estrangeiras
- Cardinalidade
- DER
- Joins
- Organização de tabelas

---

# 📁 Estrutura

Crie:

tarefas/

└── tarefa04/
- respostas.txt
└── imagens/

---

# 📌 Instruções

Para cada exercício:

1. Modele as tabelas corretamente
2. Defina PK e FK
3. Identifique cardinalidade
4. (Opcional) Desenhe o DER
5. Escreva exemplos de JOIN

---

# 🟢 Exercício 1 — Cliente e Pedido

Cenário:

Um cliente pode fazer vários pedidos.

### Perguntas:

1. Quais tabelas existem?
2. Qual a cardinalidade?
3. Defina PK e FK
4. Faça um INNER JOIN

---

# 🟢 Exercício 2 — Pedido e Produto

Cenário:

Um pedido pode ter vários produtos  
Um produto pode estar em vários pedidos

### Perguntas:

1. Qual o tipo de relacionamento?
2. Qual tabela intermediária criar?
3. Defina chave composta
4. Monte as tabelas

---

# 🟡 Exercício 3 — Funcionário e Departamento

Cenário:

Um funcionário pertence a um departamento  
Um departamento tem vários funcionários

### Perguntas:

1. Qual a cardinalidade?
2. Onde fica a FK?
3. Modele as tabelas

---

# 🟡 Exercício 4 — Índices

Cenário:

Tabela de pedidos com milhares de registros

### Perguntas:

1. Em quais colunas você criaria índices?
2. Por quê?

---

# 🔴 Exercício 5 — Sistema Escolar

Cenário:

- Alunos fazem disciplinas
- Professores ministram disciplinas

### Perguntas:

1. Identifique relacionamentos
2. Existe N:N?
3. Quais tabelas criar?
4. Monte o DER (imagem)

---

# 🔴 Exercício 6 — Joins

Dadas as tabelas:

Clientes  
Pedidos  

### Perguntas:

1. Faça:
   - INNER JOIN
   - LEFT JOIN
2. Explique a diferença

---

# 🔴 Exercício 7 - Preparação para próxima etapa - Instalação

Instale um banco postgres em sua máquina, se possível tente reproduzir os cenários acima já rodando nele.

