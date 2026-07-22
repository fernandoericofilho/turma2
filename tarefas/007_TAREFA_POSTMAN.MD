# 🚀 Desafio Extra — Postman Pre-request Script e Tests Script

## 💡 Objetivo

Aprender a utilizar automações dentro do Postman:

- Pre-request Script
- Tests Script
- Variáveis
- Captura de dados da resposta
- Reutilização entre requests

---

# 📌 O que é Pre-request Script?

É um script executado:

```text
ANTES da requisição HTTP ser enviada
```

---

# 🎯 Para que serve?

Usos comuns:

- gerar token
- criar data dinâmica
- gerar IDs aleatórios
- configurar variáveis
- preparar autenticação

---

# 📌 O que é Tests Script?

É um script executado:

```text
DEPOIS da resposta HTTP voltar
```

---

# 🎯 Para que serve?

Usos comuns:

- validar status HTTP
- validar JSON
- salvar IDs
- validar tempo de resposta
- automatizar testes

---

# 🧪 Exercício 1 — Gerar nome aleatório no Pre-request

## 📌 Objetivo

Gerar automaticamente um título antes do POST.

---

# 📍 Endpoint

```http
POST /livros
```

---

# 🚀 Pre-request Script

```javascript
const numero = Math.floor(Math.random() * 1000);

pm.environment.set(
    "tituloLivro",
    "Livro_" + numero
);
```

---

# 💡 Explicação

## Math.random()

Gera número aleatório.

---

## pm.environment.set()

Cria variável no Postman.

---

# 📦 Body da requisição

```json
{
  "titulo": "{{tituloLivro}}",
  "autor": "Robert Martin"
}
```

---

# 🎯 Resultado esperado

A cada request:

```text
Livro_123
Livro_456
Livro_789
```

---

# 🧪 Exercício 2 — Validar status HTTP

## 📍 Tests Script

```javascript
pm.test("Status deve ser 201", function () {
    pm.response.to.have.status(201);
});
```

---

# 💡 Explicação

## pm.test()

Cria um teste.

---

## pm.response

Representa a resposta HTTP.

---

## to.have.status()

Valida status HTTP.

---

# 🧪 Exercício 3 — Validar JSON

## 📍 Tests Script

```javascript
pm.test("Response possui id", function () {

    const json = pm.response.json();

    pm.expect(json.id).to.not.be.null;
});
```

---

# 💡 Explicação

## pm.response.json()

Transforma response em JSON.

---

## pm.expect()

Faz validações.

---

# 🧪 Exercício 4 — Salvar ID retornado

## 📍 Tests Script

```javascript
const json = pm.response.json();

pm.environment.set(
    "livroId",
    json.id
);
```

---

# 🎯 Objetivo

Salvar automaticamente o ID criado.

---

# 📌 Exemplo

Após POST:

```json
{
  "id": 5,
  "titulo": "Clean Code"
}
```

O Postman salva:

```text
livroId = 5
```

---

# 🚀 Exercício 5 — Reutilizar variável

## 📍 Endpoint

```http
GET /livros/{{livroId}}
```

---

# 💡 Objetivo

Usar automaticamente o ID salvo anteriormente.

---

# 🧪 Exercício 6 — Validar tempo de resposta

## 📍 Tests Script

```javascript
pm.test("Tempo menor que 500ms", function () {

    pm.expect(
        pm.response.responseTime
    ).to.be.below(500);
});
```

---

# 🎯 Objetivo

Validar performance da API.

---

# 🧪 Exercício 7 — Validar campo específico

## 📍 Tests Script

```javascript
pm.test("Título correto", function () {

    const json = pm.response.json();

    pm.expect(json.titulo)
        .to.eql("Clean Code");
});
```

---

# 🧪 Exercício 8 — Validar lista

## 📍 Endpoint

```http
GET /livros
```

---

# 📍 Tests Script

```javascript
pm.test("Lista não vazia", function () {

    const json = pm.response.json();

    pm.expect(json.content.length)
        .to.be.above(0);
});
```

---

# 📌 O que é pm ?

`pm` significa:

```text
Postman API
```

É o objeto utilizado pelo Postman para acessar:

- request
- response
- body
- headers
- variáveis
- autenticação
- tempo de resposta

---

# 📚 Principais comandos

| Comando | Função |
|---|---|
| `pm.test()` | Cria teste |
| `pm.expect()` | Faz validação |
| `pm.response.json()` | Converte resposta |
| `pm.environment.set()` | Cria variável |
| `pm.environment.get()` | Busca variável |
| `pm.response.code` | Status HTTP |
| `pm.response.responseTime` | Tempo resposta |

---

# 🔥 Desafio Extra

## Criar fluxo automático:

### 1️⃣ POST cria livro

↓

### 2️⃣ Captura ID automaticamente

↓

### 3️⃣ GET usa ID salvo

↓

### 4️⃣ PUT atualiza livro

↓

### 5️⃣ DELETE remove livro

---

# 🎯 Objetivo final

Aprender automação básica de APIs utilizando Postman como ferramenta profissional de testes REST.
