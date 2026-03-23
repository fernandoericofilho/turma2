## Exercício 7 — Pedido completo

1. Identifique as dependências funcionais

- *cliente_cidade* depende de *cliente_nome*, não da chave *pedido_id*
- *preco* depende de *produto_nome*, não do *pedido_id*

2. Normalize até **3FN**

cliente

| cliente_id | cliente_nome | cliente_cidade | 
|:----------:|:------------:|:--------------:|
|     1      |     João     |     Recife     | 
|     2      |    Maria     |   São Paulo    |

pedido

| pedido_id | cliente_id |
|:---------:|:----------:|
|     1     |     1      |
|     2     |     2      |

produto

| produto_id | produto_nome | preco |
|:----------:|:------------:|:-----:|
|     1      |   Notebook   | 4000  |
|     2      |    Mouse     |  50   |
|     3      |   Teclado    |  120  |

itens_pedido

| item_id | pedido_id | produto_id |
|:-------:|:---------:|:----------:|
|    1    |     1     |     1      |
|    2    |     1     |     2      |
|    3    |     2     |     3      |

---

## Exercício 8 — Venda e vendedor

1. Existe dependência transitiva?

- sim, *loja_cidade* depende de *loja_nome*, que depende de *vendedor_nome*

2. Como dividir as tabelas?

venda

| venda_id | vendedor_id | 
|:--------:|:-----------:|
|    1     |      1      |
|    2     |      1      | 
|    3     |      2      |

vendedor

| vendedor_id | vendedor_nome | loja_id |
|:-----------:|:-------------:|:-------:|
|      1      |    Carlos     |    1    |
|      2      |      Ana      |    2    |

loja

| loja_id |  loja_nome  | loja_cidade |
|:-------:|:-----------:|:-----------:|
|    1    | Loja centro |   Recife    |
|    2    | Loja Norte  |  São Paulo  |

---

## Exercício 9 — Sistema de universidade

1. Existe relação **N:N**?

- sim, um aluno pode ter várias disciplinas e uma disciplina pode ter vários alunos

2. Quais tabelas devem existir?

aluno

| aluno_id | aluno_nome | 
|:--------:|:----------:|
|    1     |    Ana     |
|    2     |   Pedro    |

disciplina

| disciplina_id | disciplina_nome | professor |
|:-------------:|:---------------:|:---------:|
|       1       | Banco de Dados  |  Marcos   |
|       2       |      Redes      |   Carla   |

matricula

| matricula_id | aluno_id | disciplina_id |
|:------------:|:--------:|:-------------:|
|      1       |    1     |       1       |
|      2       |    2     |       1       |
|      3       |    1     |       2       |

---

## Exercício 10 — Sistema de e-commerce

1. Identifique dependências funcionais

- *cliente_email* depende de *cliente_nome*, não do pedido
- *categoria* depende de *produto_nome*, não do pedido

2. Normalize até **3FN**
3. Quais seriam as tabelas finais?

cliente

| cliente_id | cliente_nome | cliente_email  |
|:----------:|:------------:|:--------------:|
|     1      |     João     | joao@email.com |
|     2      |     Ana      | ana@email.com  |

pedido

| pedido_id | cliente_id |
|:---------:|:----------:|
|     1     |     1      |
|     2     |     2      |

produto

| produto_id | produto_nome | categoria_id | 
|:----------:|:------------:|:------------:|
|     1      |   Notebook   |      1       |
|     2      |    Mouse     |      1       |
|     3      |  Geladeira   |      2       |

categoria

| categoria_id |  categoria_nome  |
|:------------:|:----------------:|
|      1       |   Informática    |
|      2       | Eletrodomésticos |

itens_pedido

| item_id | pedido_id | produto_id |
|:-------:|:---------:|:----------:|
|    1    |     1     |     1      |
|    2    |     1     |     2      |
|    3    |     2     |     3      |

---