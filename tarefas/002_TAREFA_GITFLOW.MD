# 🐱🚀 Exercício Prático – Gitflow Completo com Evolução de Produto

## 🎯 Objetivo

Praticar o fluxo completo do **Gitflow**, incluindo:

- feature
- release
- hotfix
- múltiplas features simultâneas
- resolução de conflito
- versionamento semântico

Ao final, você entenderá na prática como um produto evolui em produção.

---

# 📦 Contexto

Você está desenvolvendo um pequeno “produto” chamado:

🐱 **Diário do Gatinho**

O sistema começou simples, mas novas funcionalidades e correções precisarão ser feitas seguindo o fluxo correto de versionamento.

---

# 🏁 Situação Inicial

Sua branch `master` está vazia.

---

# 1️⃣ Feature 1 – Criar frase inicial

## 🎯 Objetivo

Criar a funcionalidade inicial do sistema.

## 📌 Passos

### Criar branch:

```bash
git checkout master
git pull origin master
git checkout -b feature/frase-inicial
```

### Criar arquivo:

`frase.txt`

Conteúdo:

```
Meu gatinho acordou.
```

### Commit:

```bash
git add frase.txt
git commit -m "feat: adiciona frase inicial do gatinho"
```

---

# 2️⃣ Feature 2 – Adicionar ação do dia

Agora o gatinho precisa fazer algo.

## 📌 Criar nova branch a partir da master

⚠️ Não criar a partir da feature anterior.

```bash
git checkout master
git checkout -b feature/acao-do-dia
```

### Atualizar `frase.txt` para:

```
Meu gatinho acordou e foi brincar.
```

### Commit:

```bash
git add frase.txt
git commit -m "feat: adiciona ação do dia do gatinho"
```

---

# 3️⃣ Release v1.0.0

Agora vamos preparar a primeira versão oficial.

## 📌 Criar branch de release

A partir da master após merge das features:

```bash
git checkout master
git merge feature/frase-inicial
git merge feature/acao-do-dia

git checkout -b release/v1.0.0
```

### Ajuste final de texto (refinamento de release)

Atualize `frase.txt` para:

```
Meu gatinho acordou cedo e foi brincar no jardim.
```

### Commit:

```bash
git add frase.txt
git commit -m "chore: ajusta texto final para release v1.0.0"
```

---

# 4️⃣ Finalizar Release

```bash
git checkout master
git merge release/v1.0.0
```

Opcional (boa prática):

```bash
git tag v1.0.0
```

---

# 🚨 5️⃣ Hotfix – Corrigir erro em produção

Após subir para produção, descobriram que o gatinho não brincava no jardim… ele brincava na sala 😱

## 📌 Criar hotfix

```bash
git checkout master
git checkout -b hotfix/corrige-local-brincadeira
```

### Atualizar `frase.txt` para:

```
Meu gatinho acordou cedo e foi brincar na sala.
```

### Commit:

```bash
git add frase.txt
git commit -m "fix: corrige local onde gatinho brinca"
```

### Finalizar hotfix

```bash
git checkout master
git merge hotfix/corrige-local-brincadeira
git tag v1.0.1
```

---

# ✨ 6️⃣ Nova Feature – Humor do gatinho (v1.1.0)

Agora vamos evoluir o produto.

## 📌 Criar nova feature

```bash
git checkout master
git checkout -b feature/humor-gatinho
```

### Atualizar `frase.txt`:

```
Meu gatinho acordou cedo, foi brincar na sala e ficou muito feliz!
```

### Commit:

```bash
git add frase.txt
git commit -m "feat: adiciona humor do gatinho"
```

---

# 🚀 Criar Release v1.1.0

```bash
git checkout master
git merge feature/humor-gatinho

git checkout -b release/v1.1.0
```

Nenhum ajuste necessário.

Finalizar:

```bash
git checkout master
git merge release/v1.1.0
git tag v1.1.0
```

---

# 🧠 Desafio Extra – Conflito de Merge

Simule conflito:

1. Crie uma branch:
```
feature/adiciona-exclamacao
```

2. Altere a frase final para:

```
Meu gatinho acordou cedo, foi brincar na sala e ficou muito feliz!!!
```

3. Enquanto isso, na master altere manualmente para:

```
Meu gatinho acordou cedo e foi brincar na sala.
```

4. Tente fazer merge.

🔥 Resolva o conflito manualmente.

---

# ✅ Resultado Final Esperado na Master

```
Meu gatinho acordou cedo, foi brincar na sala e ficou muito feliz!!!
```

---

# 📸 Entrega

Você deve entregar:

1. Print do comando:

```bash
git log --graph --all --oneline --decorate
```

2. Print mostrando as tags:

```bash
git tag
```

3. Breve explicação respondendo:

- Qual a diferença entre feature, release e hotfix?
- Por que hotfix sai da master?
- O que é versionamento semântico?
- O que aprendeu sobre conflitos?

---

# 📚 O que você praticou nesse exercício

✔️ Criação de features independentes  
✔️ Merge correto para master  
✔️ Criação de release  
✔️ Tag de versão  
✔️ Hotfix em produção  
✔️ Evolução de produto  
✔️ Resolução de conflito  
✔️ Versionamento semântico (MAJOR.MINOR.PATCH)

---

# 🏷️ Dica sobre Versionamento

- MAJOR → Mudança grande / quebra compatibilidade
- MINOR → Nova funcionalidade
- PATCH → Correção de bug

Exemplo:

v1.0.0  
v1.0.1  
v1.1.0  
v2.0.0  

