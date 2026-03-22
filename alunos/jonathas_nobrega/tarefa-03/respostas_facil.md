Exercicio 1 - Telefones de Cliente:

Tabela de telefones
| cliente_id | nome | telefones |
| ---------- | ---- | -------- |
| 1 | João | 9999-1111, 9888-2222 |
| 2 | Maria | 9888-2222 |

Q1: A tabela está na na **1FN**?
R1: Não está na **1FN** porque a coluna "telefones" possui múltiplos valores no mesmo campo.

Q2: Como modelar corretamente os telefones?
R2: Criando outra tabela para armazenar os telefones para cada cliente.

Tabela de clientes
| cliente_id | nome |
| ---------- | ---- |
| 1 | João |
| 2 | Maria |

Tabela de telefones
| telefone_id | cliente_id | telefone |
| ----------- | ---------- | -------- |
| 1 | 1 | 9999-1111 |
| 2 | 1 | 9888-2222 |
| 3 | 2 | 9777-3333 |

Exercicio 2 - Pedido com vários produtos:

Tabela inicial
| pedido_id | cliente | produtos |
| --------- | ------- | -------- |
| 1 | João | arroz, feijão |
| 2 | Maria | leite |

Q1: Qual problema de normalização existe?
R1: A tabela não está na **1FN** porque a coluna produtos possui múltiplos valores no mesmo campo.

Q2: Como ficaria a estrutura após aplicar **1FN**?
R2: Com os produtos separados numa tábela própria.

Tabela de Pedidos
| pedido_id | cliente |
| --------- | ------- |
| 1 | João |
| 2 | Maria |

Tabela de Itens e Pedidos
| item_id | pedido_id | produto |
| ------- | --------- | ------- |
| 1 | 1 | arroz |
| 2 | 1 | feijão |
| 3 | 2 | leite |

Exercicio 3 - Curso e alunos:

Tabela inicial
| curso_id | curso_nome | alunos |
| -------- | ---------- | ------ |
| 1 | Java | Ana, Carlos, Pedro |

Q1: Qual regra da **1FN** está sendo violada?
R1: A coluna alunos possui vários valores na mesma célula:

Q2: Como ficaria a modelagem correta?
R2: Separando os alunos em outra tabela, para que cada linha tenha apenas um valor por campo.

Tabela de Cursos
| curso_id | curso_nome |
| -------- | ---------- |
| 1 | Java |

Tabela de Alunos
| aluno_id | curso_id | aluno |
| -------- | -------- | ----- |
| 1 | 1 | Ana |
| 2 | 1 | Carlos |
| 3 | 1 | Pedro |

Tabela de Curso Aluno
| curso_aluno_id | curso_id | aluno_id |
| -------------- | -------- | -------- |
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 1 | 3 |
