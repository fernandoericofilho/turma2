# 🧪 Tarefa 03 — Exercícios de Normalização
## Praticando 1FN, 2FN e 3FN

Nesta tarefa você irá praticar **normalização de banco de dados** aplicando:

1. **Primeira Forma Normal (1FN)**
2. **Segunda Forma Normal (2FN)**
3. **Terceira Forma Normal (3FN)**

---

# 📁 Instruções da Tarefa

Antes de iniciar os exercícios, siga os passos abaixo.

## 1️⃣ Criar a pasta da tarefa

Dentro do repositório do projeto, crie a seguinte estrutura:


tarefas/

└── tarefa03/
- respostas_facil.txt
- respostas_medio.txt
- respostas_dificil.txt

└──── imagens/


A pasta **imagens** será utilizada para armazenar os prints das tabelas criadas.

---

## 2️⃣ Resolver os exercícios

Para cada exercício:

- Normalize a tabela até a **3FN**
- Crie as **tabelas resultantes**
- Gere um **print das tabelas finais**

Os prints devem ser armazenados na pasta:


tarefas/tarefa03/imagens


---

## 3️⃣ Nome dos arquivos de imagem

As imagens devem seguir o padrão:


numero_do_exercicio.png


Exemplo:


1.png
2.png
3.png
4.png


Cada imagem deve mostrar **as tabelas finais após a normalização**.

---

## 4️⃣ Uso do Git (obrigatório)

Utilize **commits separados por nível de dificuldade**.

### Estrutura sugerida de commits


git add .
git commit -m "tarefa03: exercícios fáceis de normalização"

git add .
git commit -m "tarefa03: exercícios intermediários de normalização"

git add .
git commit -m "tarefa03: exercícios difíceis de normalização"


Isso ajuda a manter um **histórico organizado no repositório**.

---

# 🟢 Exercícios Fáceis

## Exercício 1 — Telefones de Cliente

Tabela inicial:

| cliente_id | nome | telefones |
|---|---|---|
| 1 | João | 9999-1111, 9888-2222 |
| 2 | Maria | 9777-3333 |

Perguntas:

1. A tabela está na **1FN**?
2. Como modelar corretamente os telefones?

---

## Exercício 2 — Pedido com vários produtos

Tabela inicial:

| pedido_id | cliente | produtos |
|---|---|---|
| 1 | João | arroz, feijão |
| 2 | Maria | leite |

Perguntas:

1. Qual problema de normalização existe?
2. Como ficaria a estrutura após aplicar **1FN**?

---

## Exercício 3 — Curso e alunos

Tabela inicial:

| curso_id | curso_nome | alunos |
|---|---|---|
| 1 | Java | Ana, Carlos, Pedro |

Perguntas:

1. Qual regra da **1FN** está sendo violada?
2. Como ficaria a modelagem correta?

---

# 🟡 Exercícios Intermediários

## Exercício 4 — Funcionários e Departamento

Tabela inicial:

| funcionario_id | nome | departamento | gerente_departamento |
|---|---|---|---|
| 1 | João | TI | Carlos |
| 2 | Ana | TI | Carlos |
| 3 | Pedro | RH | Maria |

Perguntas:

1. Existe **dependência transitiva**?
2. A tabela está na **3FN**?
3. Como separar corretamente?

---

## Exercício 5 — Produtos e Categoria

Tabela inicial:

| produto_id | produto_nome | categoria_id | categoria_nome |
|---|---|---|---|
| 1 | Notebook | 10 | Informática |
| 2 | Mouse | 10 | Informática |
| 3 | Geladeira | 20 | Eletrodomésticos |

Perguntas:

1. Qual dependência funcional existe?
2. Como ficaria a tabela na **3FN**?

---

## Exercício 6 — Matrícula de alunos

Tabela inicial:

| aluno_id | aluno_nome | curso_id | curso_nome |
|---|---|---|---|
| 1 | Ana | 101 | Engenharia |
| 2 | Pedro | 101 | Engenharia |
| 3 | Carlos | 102 | Direito |

Perguntas:

1. Quais atributos dependem de quais?
2. Separe as tabelas corretamente.

---

# 🔴 Exercícios Difíceis

## Exercício 7 — Pedido completo

Tabela inicial:

| pedido_id | cliente_nome | cliente_cidade | produto_nome | preco |
|---|---|---|---|---|
| 1 | João | Recife | Notebook | 4000 |
| 1 | João | Recife | Mouse | 50 |
| 2 | Maria | São Paulo | Teclado | 120 |

Perguntas:

1. Identifique as dependências funcionais
2. Normalize até **3FN**

---

## Exercício 8 — Venda e vendedor

Tabela inicial:

| venda_id | vendedor_nome | loja_nome | loja_cidade |
|---|---|---|---|
| 1 | Carlos | Loja Centro | Recife |
| 2 | Carlos | Loja Centro | Recife |
| 3 | Ana | Loja Norte | São Paulo |

Perguntas:

1. Existe dependência transitiva?
2. Como dividir as tabelas?

---

## Exercício 9 — Sistema de universidade

Tabela inicial:

| aluno_id | aluno_nome | disciplina | professor |
|---|---|---|---|
| 1 | Ana | Banco de Dados | Marcos |
| 2 | Pedro | Banco de Dados | Marcos |
| 3 | Ana | Redes | Carla |

Perguntas:

1. Existe relação **N:N**?
2. Quais tabelas devem existir?

---

## Exercício 10 — Sistema de e-commerce

Tabela inicial:

| pedido_id | cliente_nome | cliente_email | produto_nome | categoria |
|---|---|---|---|---|
| 1 | João | joao@email.com | Notebook | Informática |
| 1 | João | joao@email.com | Mouse | Informática |
| 2 | Ana | ana@email.com | Geladeira | Eletrodomésticos |

Perguntas:

1. Identifique dependências funcionais
2. Normalize até **3FN**
3. Quais seriam as tabelas finais?
