# 📝 Tarefa — API REST Biblioteca com Spring Boot

## 💡 Objetivo

Criar uma API REST completa utilizando boas práticas de mercado com:

- Spring Boot
- Controllers REST
- DTO
- Mapper
- Validation
- Paginação
- Repository
- Service
- Postman
- HTTP Status
- Verbos HTTP

---

# 🎯 Objetivo didático

Este exercício tem como objetivo ensinar:

- separação de responsabilidades
- arquitetura em camadas
- desacoplamento entre HTTP e banco
- uso correto de DTO
- uso de Request e Response
- validação automática
- padronização REST
- boas práticas utilizadas no mercado

---

# 🧱 Arquitetura esperada

```text
HTTP Request
    ↓
Controller
    ↓
Request
    ↓
Mapper
    ↓
DTO
    ↓
Service
    ↓
Mapper
    ↓
Entity
    ↓
Repository
    ↓
Banco
```

## 🔁 Retorno

```text
Banco
    ↓
Repository
    ↓
Entity
    ↓
Mapper
    ↓
DTO
    ↓
Mapper
    ↓
Response
    ↓
Controller
    ↓
HTTP Response
```

---

# 📌 Regras obrigatórias

## ✅ Controller

Responsável por:

- receber requisições HTTP
- validar dados
- retornar status HTTP
- retornar responses
- chamar services

O controller NÃO deve:

- acessar repository
- conter regra de negócio
- retornar entity diretamente

---

## ✅ Service

Responsável por:

- regras de negócio
- orquestração
- conversão DTO ↔ Entity

---

## ✅ Repository

Responsável apenas por:

- acesso ao banco
- consultas JPA

---

# 📦 Estrutura esperada

```text
controllers/
    LivroController

controllers/request/
    LivroRequest

controllers/response/
    LivroResponse

dtos/
    LivroDTO

mappers/
    LivroMapper

services/
    LivroService

repositories/
    LivroRepository

models/
    Livro
```

---

# 🚀 Parte 1 — Criar Request

Criar:

```text
controllers/request/LivroRequest.java
```

Campos:

- titulo
- autor

---

# ✅ Validation obrigatória

Utilizar:

```java
@NotBlank
```

Exemplo:

```java
@NotBlank(message = "Título obrigatório")
private String titulo;
```

---

# 📚 Principais anotações de Validation

| Anotação | Função |
|---|---|
| `@NotBlank` | Campo obrigatório texto |
| `@NotNull` | Não pode ser nulo |
| `@Size` | Tamanho mínimo/máximo |
| `@Email` | Validar email |
| `@Min` | Valor mínimo |
| `@Max` | Valor máximo |

---

# 💡 Exemplo completo

```java
@NotBlank(message = "Título obrigatório")
@Size(min = 3, max = 100)
private String titulo;
```

---

# ✅ Ativando validation no controller

Utilizar:

```java
@Valid
```

Exemplo:

```java
@PostMapping
public ResponseEntity<LivroResponse> cadastrar(
        @RequestBody @Valid LivroRequest request
)
```

---

# ❌ Exemplo inválido

```json
{
  "titulo": "",
  "autor": ""
}
```

---

# ✅ Resultado esperado

```http
400 BAD REQUEST
```

---

# 🚀 Parte 2 — Criar Response

Criar:

```text
controllers/response/LivroResponse.java
```

Retornar apenas:

- id
- titulo
- autor

---

# 💡 Objetivo

Mostrar aos alunos que:

- nem tudo que entra deve ser salvo
- nem tudo que existe no banco deve ser retornado

---

# 🚀 Parte 3 — Criar DTO

Criar:

```text
dtos/LivroDTO.java
```

Objetivo:

- trafegar dados entre camadas
- desacoplar HTTP da regra de negócio

---

# 🚀 Parte 4 — Criar Mapper

Criar:

```text
mappers/LivroMapper.java
```

---

# 🔄 Conversões obrigatórias

## Request → DTO

```java
LivroDTO toDTO(LivroRequest request)
```

---

## DTO → Entity

```java
Livro toEntity(LivroDTO dto)
```

---

## Entity → DTO

```java
LivroDTO toDTO(Livro livro)
```

---

## DTO → Response

```java
LivroResponse toResponse(LivroDTO dto)
```

---

# 💡 Objetivo do Mapper

Evitar:

- acoplamento
- repetição de código
- exposição indevida de entidades

---

# 🚀 Parte 5 — Refatorar Service

---

# ❌ ERRADO

```java
public Livro cadastrar(Livro livro)
```

---

# ✅ CORRETO

```java
public LivroDTO cadastrar(LivroDTO dto)
```

---

# 🔄 Fluxo obrigatório do Service

```text
DTO
 ↓
Entity
 ↓
Repository
 ↓
Entity
 ↓
DTO
```

---

# 🚀 Parte 6 — Criar LivroController

Base URL:

```http
/livros
```

---

# 📚 Anotações obrigatórias do Controller

| Anotação | Função |
|---|---|
| `@RestController` | Define controller REST |
| `@RequestMapping` | Define rota base |
| `@GetMapping` | Endpoint GET |
| `@PostMapping` | Endpoint POST |
| `@PutMapping` | Endpoint PUT |
| `@DeleteMapping` | Endpoint DELETE |
| `@RequestBody` | Captura JSON |
| `@RequestParam` | Captura query params |
| `@PathVariable` | Captura parâmetros da URL |
| `@Valid` | Ativa validation |

---

# 🚀 Endpoint 1 — POST

```http
POST /livros
```

---

# ✅ Regras

- usar `@RequestBody`
- usar `@Valid`
- receber Request
- retornar Response
- retornar `201 CREATED`

---

# 📥 Exemplo Request

```json
{
  "titulo": "Effective Java",
  "autor": "Joshua Bloch"
}
```

---

# 📤 Exemplo Response

```json
{
  "id": 1,
  "titulo": "Effective Java",
  "autor": "Joshua Bloch"
}
```

---

# 🚀 Endpoint 2 — GET paginado

```http
GET /livros?page=0&size=5
```

---

# ✅ Regras

Utilizar:

```java
Pageable pageable
```

Retornar:

```java
Page<LivroResponse>
```

---

# 🚀 Endpoint 3 — GET por ID

```http
GET /livros/{id}
```

---

# ✅ Regras

Utilizar:

```java
@PathVariable Long id
```

Retornar:

- 200 OK
- 404 NOT FOUND

---

# 🚀 Endpoint 4 — GET por título

```http
GET /livros/titulo?titulo=Clean Code
```

---

# ✅ Regras

Utilizar:

```java
@RequestParam String titulo
```

---

# 🚀 Endpoint 5 — PUT

```http
PUT /livros/{id}
```

---

# ✅ Regras

- atualizar:
  - titulo
  - autor
- receber Request
- retornar Response

---

# 🚀 Endpoint 6 — DELETE

```http
DELETE /livros/{id}
```

---

# ✅ Retorno esperado

```http
204 NO CONTENT
```

---

# 📄 Status HTTP obrigatórios

| Operação | Status |
|---|---|
| GET | 200 OK |
| POST | 201 CREATED |
| DELETE | 204 NO CONTENT |
| Validation | 400 BAD REQUEST |
| Não encontrado | 404 NOT FOUND |

---

# 🚀 Parte 7 — Paginação

Alterar repository:

```java
Page<Livro> findAll(Pageable pageable)
```

---

# 💡 Objetivo

Ensinar:

- performance
- limitação de registros
- APIs escaláveis

---

# 🚀 Parte 8 — Postman

Criar collection contendo:

- POST
- GET
- GET por ID
- GET por título
- PUT
- DELETE

---

# 🧪 Scripts obrigatórios no Postman

## ✅ Validar status HTTP

```javascript
pm.test("Status 200", function () {
    pm.response.to.have.status(200);
});
```

---

# 💡 O que é pm ?

`pm` significa:

```text
Postman API
```

É o objeto do Postman para acessar:

- response
- request
- headers
- body
- variáveis
- status HTTP

---

# ✅ Validar JSON

```javascript
pm.test("Tem titulo", function () {

    const json = pm.response.json();

    pm.expect(json.titulo).to.not.be.null;
});
```

---

# 🚀 Parte 9 — Exportar Collection

Exportar:

```text
Biblioteca_API.postman_collection.json
```

---

# 🎯 Conceitos avaliados

- REST
- HTTP
- Verbos HTTP
- Status HTTP
- Validation
- DTO
- Mapper
- Paginação
- Spring Boot
- Postman
- Arquitetura em camadas
- Responsabilidade das camadas
- Boas práticas REST

---

# 🔥 Desafio Extra

Criar:

```text
GlobalExceptionHandler
```

Utilizando:

```java
@RestControllerAdvice
```

Para tratamento global de erros.

---

# 🏁 Objetivo final

Construir uma API semelhante às utilizadas em projetos reais de mercado:

- desacoplada
- organizada
- reutilizável
- validada
- escalável
- padronizada
