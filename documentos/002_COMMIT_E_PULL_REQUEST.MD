# 📘 Guia Prático – Commit e Pull Request no Git (Para Iniciantes)

Este guia explica passo a passo como trabalhar com branches, commits e Pull Requests (PRs) usando Git e GitHub.

---

# 🔁 Visão Geral do Fluxo

Fluxo padrão de trabalho em equipe:

1. Atualizar a `master`
2. Criar uma branch nova
3. Desenvolver
4. Fazer commits organizados
5. Atualizar sua branch com a `master`
6. Subir a branch
7. Abrir Pull Request
8. Ajustar se necessário

---


# 🌳 O que é uma Branch?

Uma branch é uma linha de desenvolvimento separada.

Ela permite que você trabalhe sem afetar a `master` (branch principal do projeto).

Exemplo:


master
└── feature/login-api


---

# 1️⃣ Atualizar a master antes de tudo

Sempre comece garantindo que sua `master` está atualizada:

```
git checkout master
git pull origin master
```
Isso evita começar seu trabalho com código desatualizado.

# 2️⃣ Criar sua branch a partir da master

Crie sua branch:

```
git checkout -b feature/cadastro-cliente
```
Ou usando comando mais moderno:
```
git switch -c feature/cadastro-cliente
```

# 📌 Padrão de nomes recomendados

```
Tipo	Exemplo
Nova funcionalidade	feature/cadastro-cliente
Correção de bug	bugfix/erro-validacao-cpf
Refatoração	refactor/service-pagamento
Documentação	docs/atualiza-readme
```

# 3️⃣ Atualizar sua branch antes de começar

```
git fetch origin
git merge origin/master
```

Ou:

```
git rebase origin/master
```

Para iniciantes, prefira merge.

# 4️⃣ Desenvolver normalmente

Faça suas alterações no código.

# 5️⃣ Verificar o que mudou
git status

Para ver diferenças:

```
git diff
```

# 6️⃣ Adicionar arquivos ao commit

Adicionar arquivo específico:

```
git add src/service/ClienteService.java
```

Adicionar tudo:

```
git add .
```

# 7️⃣ Criar um commit
```
git commit -m "feat: adiciona validação de CPF no cadastro"
```

# ✍️ Como escrever boas mensagens de commit

Formato recomendado:
tipo: descrição curta no imperativo

```
Exemplos bons
git commit -m "feat: adiciona endpoint de cadastro"
git commit -m "fix: corrige erro na validação"
git commit -m "refactor: simplifica regra de cálculo"
git commit -m "docs: adiciona documentação da API"
git commit -m "test: adiciona testes do ClienteService"
Exemplos ruins
git commit -m "update"
git commit -m "alterações"
git commit -m "arrumando coisa"
```

# 📦 Tipos mais usados

Prefixo	Quando usar

```
feat:	Nova funcionalidade
fix:	Correção de bug
refactor:	Mudança interna sem alterar regra
docs:	Documentação
test:	Testes
chore:	Ajustes técnicos
style:	Formatação
```

# 8️⃣ Atualizar sua branch antes de subir

```
git fetch origin
git merge origin/master
```

## Resolva conflitos se aparecerem.

# 9️⃣ Subir sua branch

```
git push origin feature/cadastro-cliente
```

Na primeira vez:

```
git push -u origin feature/cadastro-cliente
```

# 🔟 Abrir Pull Request (PR)

No GitHub:

Vá no repositório

Clique em “Compare & pull request”

Base: master

Compare: sua branch

Escreva título e descrição claros

# 📝 Como escrever um bom PR

## Título
feat: adiciona validação de CPF no cadastro
Descrição
## O que foi feito
Adicionada validação de CPF no cadastro.

## Por que
Evitar dados inválidos no sistema.

## Como testar
- Criar cliente com CPF inválido → deve retornar erro
- Criar cliente com CPF válido → deve salvar corretamente
1️⃣1️⃣ Ajustes após revisão

Se houver comentários no PR:

```
git add .
git commit -m "fix: ajusta validação conforme revisão"
git push origin feature/cadastro-cliente
```

O PR será atualizado automaticamente.

# ⚠️ Erros Comuns

Trabalhar direto na master

Fazer commit gigante

Mensagem de commit genérica

Não atualizar a branch antes de subir

Fazer force push na master

# 🧠 Dicas Importantes

Faça commits pequenos

Teste antes de subir

Sempre rode git status

Prefira clareza

Pergunte antes de usar rebase se não souber

# 📌 Resumo Final

```
git checkout master
git pull origin master

git checkout -b feature/minha-feature
```

# desenvolve...

```
git add .
git commit -m "feat: minha alteração"

git fetch origin
git merge origin/master

git push origin feature/minha-feature
```