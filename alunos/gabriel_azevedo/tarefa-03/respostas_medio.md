## Exercício 4 — Funcionários e Departamento

1. Existe **dependência transitiva**?

- sim, *gerente_departamento* depende de *departamento*, que depende da chave primária *funcionario_id*

2. A tabela está na **3FN**?

- não, pois o gerente depende do departamento, que não é chave

3. Como separar corretamente?

funcionario

| funcionario_id | nome  | departamento_id |
|----------------|-------|-----------------|
| 1              | João  | 1               |
| 2              | Ana   | 1               |
| 3              | Pedro | 2               |

departamento

| departamento_id | nome | gerente |
|:---------------:|:----:|:-------:|
|        1        |  TI  | Carlos  |
|        2        |  RH  |  Maria  |

---

## Exercício 5 — Produtos e Categoria

1. Qual dependência funcional existe?

- *categoria_nome* depende de *categoria_id*, não depende da chave primária que é *produto_id*

2. Como ficaria a tabela na **3FN**?

produto

| produto_id | produto_nome | categoria_id |
|:----------:|:------------:|:------------:|
|     1      |   Notebook   |      10      | 
|     2      |    Mouse     |      10      | 
|     3      |  Geladeira   |      20      |

categoria

| categoria_id |  categoria_nome  |
|:------------:|:----------------:|
|      10      |   Informática    |
|      10      |   Informática    |
|      20      | Eletrodomésticos |

---

## Exercício 6 — Matrícula de alunos

1. Quais atributos dependem de quais?

- *curso_nome* depende de *curso_id*, que depende da chave primária *aluno_id*

2. Separe as tabelas corretamente.

aluno

| aluno_id | aluno_nome | curso_id |
|:--------:|:----------:|:--------:|
|    1     |    Ana     |   101    |
|    2     |   Pedro    |   101    |
|    3     |   Carlos   |   102    |

curso

| curso_id | curso_nome |
|:--------:|------------|
|   101    | Engenharia |
|   101    | Engenharia |
|   102    | Direito    |

--- 