Exercício 7 - Pedido completo:

Tabela Inicial
| pedido_id | cliente_nome | cliente_cidade | produto_nome | preco |
| --------- | ------------ | -------------- | ------------ | ----- |
| 1 | João | Recife | Notebook | 4000 |
| 1 | João | Recife | Mouse | 50 |
| 2 | Maria | São Paulo | Teclado | 120 |

Q1: Identifique as dependências funcionais
R1: Um pedido pode ter vários produtos, então a chave é composta.
(
pedido_id → cliente_nome, cliente_cidade
cliente_nome → cliente_cidade
produto_nome → preco
)

Q2: Normalize até **3FN**
R2: Precisamos separar clientes, produtos e pedidos.

1FN: A tabela já está em 1FN, não há grupos repetidos e cada campo possui valor atômico.
2FN: nas tabelas abaixo;

Tabela de Clientes
| pedido_id | cliente_nome | cliente_cidade |
| --------- | ------------ | -------------- |
| 1 | João | Recife |
| 2 | Maria | São Paulo |

Tabela de Produtos
| produto_id | nome | preco |
| ---------- | ---- | ----- |
| 1 | Notebook | 4000 |
| 2 | Mouse | 50 |
| 3 | Teclado | 120 |

Tabela de Pedidos
| pedido_id | cliente_id |
| --------- | ---------- |
| 1 | 1 |
| 2 | 2 |

Tabela de Pedidos e Produtos
| item_pedido_id | pedido_id | produto_id |
| -------------- | --------- | ---------- |
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 2 | 3 |

3FN: As tabelas continuam iguais, pois já criamos as tabelas, clientes, produtos, pedidos e pedidos e produtos, já eliminamos as dependências parciais que restavam;

Exercício 8 - Venda e vendedor

Tabela Inicial
| venda_id | vendedor_nome | loja_nome | loja_cidade |
| -------- | ------------- | --------- | ----------- |
| 1 | Carlos | Loja Centro | Recife |
| 2 | Carlos | Loja Centro | Recife |
| 3 | Ana | Loja Norte | São Paulo |

Q2: Existe dependência transitiva?
R1: venda_id -> loja_nome e loja_nome -> loja_cidade, então venda_id -> loja_cidade

Q2: Como dividir as tabelas?
R2: Exemplos abaixo;

Tabela de Lojas - loja_id → loja_nome, loja_cidade
| loja_id | loja_nome | loja_cidade |
| ------- | ----------- | ----------- |
| 1 | Loja Centro | Recife |
| 2 | Loja Norte | São Paulo |

Tabela de Vendedores - vendedor_id → vendedor_nome
| vendedor_id | vendedor_nome |
| ----------- | ------------- |
| 1 | Carlos |
| 2 | Ana |

Tabela de Vendas - venda_id → vendedor_id, loja_id
| venda_id | vendedor_id | loja_id |
| -------- | ----------- | ------- |
| 1 | 1 | 1 |
| 2 | 1 | 1 |
| 3 | 2 | 2 |

Exercício 9 - Sistema de universidade

Tabela inicial
| aluno_id | aluno_nome | disciplina | professor |
| -------- | ---------- | ---------- | --------- |
| 1 | Ana | Banco de Dados | Marcos |
| 2 | Pedro | Banco de Dados | Marcos |
| 3 | Ana | Redes | Carla |

Q1: Existe relação **N:N**?
R1: Existe uma relação muitos-para-muitos (N:N) entre: Alunos e Disciplinas

Q2: Quais tabelas devem existir?
R2: Tabelas abaixo;

Tabela de Alunos
| aluno_id | aluno_nome |
| -------- | ---------- |
| 1 | Ana |
| 2 | Pedro |

Tabela de Professores
| professor_id | professor_nome |
| ------------ | -------------- |
| 1 | Marcos |
| 2 | Carla |

Tabela de Disciplinas
| disciplina_id | disciplina_nome | professor_id |
| ------------- | --------------- | ------------ |
| 1 | Banco de Dados | 1 |
| 2 | Redes | 2 |

Tabela de Alunos e Disciplinas
| aluno_disciplina_id | aluno_id | disciplina_id |
| ------------------- | -------- | ------------- |
| 1 | 1 | 1 |
| 2 | 2 | 1 |
| 3 | 1 | 2 |

Exercício 10 - Sistema de e-commerce

Tabela Inicial
| pedido_id | cliente_nome | cliente_email | produto_nome | categoria |
| --------- | ------------ | --------------------------------------- | ------------ | ---------------- |
| 1 | João | joao@email.com | Notebook | Informática |
| 1 | João | joao@email.com | Mouse | Informática |
| 2 | Ana | ana@email.com | Geladeira | Eletrodomésticos |

Q1: Identifique dependências funcionais.
R1: Existem algumas dependências, sendo elas 1. Um pedido pertence a um cliente (pedido_id → cliente_nome, cliente_email) 2. O email identifica o cliente (cliente_email → cliente_nome) 3. Cada produto pertence a uma categoria (produto_nome → categoria) 4. Cada linha da tabela é identificada por (pedido_id, produto_nome)
Q2: Normalize até **3FN**.
R2: Abaixo;
1FN A tabela já está em 1FN, não há atributos multivalorados e os valores são atômicos.
2FN: Separamos pelas dependências parciais

Tabela de Clientes
| cliente_id | cliente_nome | cliente_email |
| ---------- | ------------ | ------------- |
| 1 | João | joao@email.com |
| 2 | Ana | ana@email.com |

Tabela de Pedidos
| pedido_id | cliente_id |
| --------- | ---------- |
| 1 | 1 |
| 2 | 2 |

Tabela de Produtos
| produto_id | produto_nome | categoria |
| ---------- | ------------ | ---------------- |
| 1 | Notebook | Informática |
| 2 | Mouse | Informática |
| 3 | Geladeira | Eletrodomésticos |

Tabela de Itens e Pedidos
| item_pedido_id | pedido_id | produto_id |
| -------------- | --------- | ---------- |
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 2 | 3 |

3FN: Agora verificamos dependências transitivas, que existem em produto_nome -> categoria

Tabela de Categorias
| categoria_id | categoria_nome |
| ------------ | ---------------- |
| 1 | Informática |
| 2 | Eletrodomésticos |

Tabela de Produtos
| produto_id | produto_nome | categoria_id |
| ---------- | ------------ | ------------ |
| 1 | Notebook | 1 |
| 2 | Mouse | 1 |
| 3 | Geladeira | 2 |

Q3: Quais seriam as tabelas finais?
R3: As tabelas finais ficam como:

Tabela de Clientes
| cliente_id | cliente_nome | cliente_email |
| ---------- | ------------ | ------------- |
| 1 | João | joao@email.com |
| 2 | Ana | ana@email.com |

Tabela de Pedidos
| pedido_id | cliente_id |
| --------- | ---------- |
| 1 | 1 |
| 2 | 2 |

Tabela de Categorias
| categoria_id | categoria_nome |
| ------------ | ---------------- |
| 1 | Informática |
| 2 | Eletrodomésticos |

Tabela de Produtos
| produto_id | produto_nome | categoria_id |
| ---------- | ------------ | ------------ |
| 1 | Notebook | 1 |
| 2 | Mouse | 1 |
| 3 | Geladeira | 2 |

Tabela de Itens e Pedidos
| item_pedido_id | pedido_id | produto_id |
| -------------- | --------- | ---------- |
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 2 | 3 |
