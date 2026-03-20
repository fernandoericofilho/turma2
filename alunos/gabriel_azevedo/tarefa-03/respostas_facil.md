## Exercício 1 — Telefones de Cliente

1. A tabela está na **1FN**?

- Não, pois na 1FN cada coluna deve possuir valores atômicos

2. Como modelar corretamente os telefones?

- Criar outra tabela para armazenar os telefones de cada cliente

cliente

| cliente_id | nome  |  
|:----------:|:-----:|  
|     1      | João  | 
|     2      | Maria |  

telefone

| telefone_id | cliente_id | telefone  |
|:-----------:|:----------:|:---------:|
|      1      |     1      | 9999-1111 |
|      2      |     1      | 9888-2222 |
|      3      |     2      | 9777-3333 |  

---

## Exercício 2 — Pedido com vários produtos

1. Qual problema de normalização existe?

- Viola a 1FN

2. Como ficaria a estrutura após aplicar **1FN**?

pedido

| pedido_id | cliente |  
|:---------:|:-------:|   
|     1     |  João   | 
|     2     |  Maria  |

itens_pedido

| item_id | pedido_id | produto |
|:-------:|:---------:|:-------:|
|    1    |     1     |  arroz  |
|    2    |     1     | feijão  |
|    3    |     2     |  leite  |

---

## Exercício 3 — Curso e alunos

1. Qual regra da **1FN** está sendo violada?

- A de possuir valores atômicos em cada célula

2. Como ficaria a modelagem correta?

curso

| curso_id | curso_nome |
|:--------:|:----------:|
|    1     |    Java    |

aluno

| aluno_id |  nome  |
|:--------:|:------:|
|    1     |  Ana   |
|    2     | Carlos |
|    3     | Pedro  | 

cursos_aluno

| id | curso_id | aluno_id |
|:--:|:--------:|:--------:|
| 1  |    1     |    1     |
| 2  |    1     |    2     |
| 3  |    1     |    3     |

---