# 🟢 Exercícios Fáceis

## Exercício 1 - Telefones de Cliente

Tabela inicial:

| cliente_id | nome | telefones |
|---|---|---|
| 1 | João | 9999-1111, 9888-2222 |
| 2 | Maria | 9777-3333 |

**1. A tabela está na 1FN?**
   
Não, pois há a presença de um atributo multivalorado (telefones) na tabela de clientes.

**2. Como modelar corretamente os telefones?**

Passando o atributo telefones para uma nova tabela `Telefones`, esta tabela também possuirá o atributo `cliente_id` como chave estrangeira, apontando para a chave primária da tabela `Clientes`.

![Modelagem do Exercício 01](imagens/exercicio01.PNG)

---

## Exercício 2 — Pedido com vários produtos

Tabela inicial:

| pedido_id | cliente | produtos |
|---|---|---|
| 1 | João | arroz, feijão |
| 2 | Maria | leite |

**1. Qual problema de normalização existe?**

Problema semelhante ao do exercício anterior, na tabela há a presença de um atributo (`produtos`) que está armazenando mais do que um valor.

**2. Como ficaria a estrutura após aplicar 1FN**?

O atributo `produtos` é convertido em uma nova tabela homônima e por se tratar de uma relação **N-N** ou **Muitos para Muitos** entre clientes e produtos, é criada uma nova tabela associativa (`Pedidos`).

![Modelagem do Exercício 02](imagens/exercicio02.PNG)

---

## Exercício 3 — Curso e alunos

Tabela inicial:

| curso_id | curso_nome | alunos |
|---|---|---|
| 1 | Java | Ana, Carlos, Pedro |

**1. Qual regra da 1FN está sendo violada?**

A tabela possui um atributo multivalorado (`alunos`) violando a 1FN que determina que os atributos de uma tabela devem ser atômicos, ou seja, devem possuir apenas um único valor.

**2. Como ficaria a modelagem correta?**

A tabela inicial seria transformada em duas tabelas `Alunos` e `Cursos`, cada uma tendo os seus respectivos atributos (`id` e `nome`), por se tratar de uma relação **N-N** ou **Muitos para Muitos** (Um curso pode ter muitos alunos e cada aluno pode se matricular em muitos cursos), é criada uma tabela associativa (`Alunos_Cursos`).

![Modelagem do Exercício 03](imagens/exercicio03.PNG)

---