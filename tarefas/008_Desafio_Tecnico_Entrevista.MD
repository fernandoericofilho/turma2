# Desafio Técnico — Payment API de Alta Concorrência

## 🎯 Objetivo

Desenvolver uma API REST de pagamentos utilizando **Java 21+ e Spring Boot**, preparada para lidar com **alto volume de requisições concorrentes**.

O objetivo não é criar uma aplicação enorme, mas demonstrar domínio de:

* Java moderno
* Spring Boot
* APIs REST
* Concorrência
* Virtual Threads
* Idempotência
* Processamento assíncrono
* CQRS
* Eventos
* Persistência e transações
* Testes automatizados
* Boas práticas de arquitetura

---

# 🏦 Cenário

Você trabalha em uma empresa de pagamentos.

A empresa possui uma API que recebe requisições para criação e consulta de pagamentos.

Em períodos de pico, a API pode receber milhares de requisições simultâneas.

O sistema precisa:

1. Criar pagamentos.
2. Consultar pagamentos.
3. Listar pagamentos de um cliente.
4. Processar pagamentos de forma assíncrona.
5. Evitar pagamentos duplicados.
6. Suportar alta concorrência.
7. Manter consistência dos dados.

---

# 🛠️ Stack obrigatória

* Java 21+
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Docker / Docker Compose
* JUnit 5
* Mockito

### Conceitos obrigatórios

* REST
* Transactions
* Idempotência
* Concorrência
* Virtual Threads
* CQRS
* Eventos
* Processamento assíncrono

### Opcional

* Kafka
* Redis
* Testcontainers
* Micrometer
* Prometheus
* Grafana

> Os itens opcionais não são necessários para concluir o desafio.

---

# 📌 Requisitos funcionais

## 1. Criar pagamento

### Endpoint

```http
POST /payments
```

Header obrigatório:

```http
Idempotency-Key: 8f3c2a10-1234
```

Request:

```json
{
  "customerId": "123",
  "amount": 150.50
}
```

Response:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "customerId": "123",
  "amount": 150.50,
  "status": "PENDING"
}
```

HTTP:

```http
201 Created
```

---

# 🔐 2. Idempotência

A API deve impedir que o mesmo pagamento seja criado duas vezes quando a mesma `Idempotency-Key` for utilizada.

Exemplo:

```text
Request 1
POST /payments
Idempotency-Key: ABC123

Request 2
POST /payments
Idempotency-Key: ABC123
```

Mesmo que as duas requisições sejam executadas simultaneamente, deve existir **apenas um pagamento**.

Resultado esperado:

```text
100 requisições
        ↓
mesma Idempotency-Key
        ↓
1 pagamento criado
99 requisições reutilizam o resultado
```

### Atenção

Não implemente apenas:

```java
if (!repository.existsByIdempotencyKey(key)) {
    repository.save(payment);
}
```

Isso possui condição de corrida.

A solução deve considerar:

* transação;
* constraint `UNIQUE`;
* tratamento de concorrência;
* atomicidade.

---

# 🔎 3. Consultar pagamento

### Endpoint

```http
GET /payments/{id}
```

Response:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "customerId": "123",
  "amount": 150.50,
  "status": "APPROVED",
  "createdAt": "2026-08-25T10:00:00Z"
}
```

---

# 👤 4. Listar pagamentos do cliente

### Endpoint

```http
GET /customers/{customerId}/payments
```

Response:

```json
[
  {
    "id": "payment-1",
    "amount": 100.00,
    "status": "APPROVED"
  },
  {
    "id": "payment-2",
    "amount": 200.00,
    "status": "PENDING"
  }
]
```

A consulta deve possuir paginação.

Exemplo:

```http
GET /customers/123/payments?page=0&size=20
```

Criar índice adequado para as consultas por `customer_id`.

---

# ⚙️ 5. Processamento assíncrono

A criação do pagamento **não deve aguardar todo o processamento**.

Fluxo esperado:

```text
POST /payments
      |
      ↓
Criar Payment
      |
      ↓
PENDING
      |
      ↓
Publicar evento
      |
      ↓
Retornar 201
```

Depois:

```text
PaymentCreated
      |
      ↓
Processador assíncrono
      |
      ↓
Processamento
      |
      +------→ APPROVED
      |
      +------→ REJECTED
```

O processamento pode inicialmente utilizar uma implementação simples com execução assíncrona.

### Evolução opcional

Substituir o mecanismo simples por:

```text
API → Kafka → Consumer → Payment Processor
```

---

# 🧵 6. Virtual Threads

A aplicação deve utilizar **Virtual Threads**.

Configure:

```properties
spring.threads.virtual.enabled=true
```

O objetivo é utilizar o recurso do Java para melhorar a capacidade de concorrência em operações predominantemente **I/O-bound**.

Você deverá ser capaz de explicar:

* O que é uma Virtual Thread.
* Diferença entre Virtual Thread e Platform Thread.
* Por que Virtual Threads ajudam em workloads I/O-bound.
* Por que elas não resolvem problemas de CPU.
* Por que Virtual Threads não eliminam gargalos de banco.
* O impacto de pools de conexão.

### Pergunta esperada

> Se sua aplicação possui 10.000 Virtual Threads, isso significa que o PostgreSQL poderá receber 10.000 conexões?

Resposta esperada:

**Não.**

O banco continua limitado pelo pool de conexões e pelos recursos disponíveis.

Exemplo:

```text
10.000 Virtual Threads
        |
        ↓
Pool JDBC
        |
        ↓
50 conexões
        |
        ↓
PostgreSQL
```

---

# 🧩 7. CQRS

Implemente uma separação clara entre operações de escrita e leitura.

### Command

Responsável por operações que alteram o estado:

```text
PaymentCommandService

- createPayment()
- processPayment()
```

### Query

Responsável pelas consultas:

```text
PaymentQueryService

- findById()
- findByCustomer()
```

Estrutura esperada:

```text
                Payment API
                     |
             +-------+-------+
             |               |
          COMMAND          QUERY
             |               |
             ↓               ↓
       CommandService   QueryService
             |               |
             ↓               ↓
         Database       Read Model
```

> Não é obrigatório utilizar dois bancos. O objetivo é demonstrar a separação de responsabilidades do CQRS.

---

# 📚 8. Eventos

Crie eventos para representar mudanças importantes no pagamento.

Exemplos:

```text
PaymentCreated
PaymentApproved
PaymentRejected
```

Uma tabela de eventos pode possuir:

```text
payment_events

id
payment_id
event_type
payload
created_at
```

Exemplo:

```text
payment_id | event_type
-----------+-----------------
123        | PAYMENT_CREATED
123        | PAYMENT_APPROVED
```

---

# 🕐 9. Event Sourcing — evolução

Não é obrigatório implementar Event Sourcing completo.

O desafio deve apenas preparar o sistema para essa evolução.

Você deve conseguir explicar:

### Modelo tradicional

```text
Payment

status = APPROVED
amount = 150
```

### Event Sourcing

```text
PaymentCreated
PaymentApproved
```

O estado poderia ser reconstruído através dos eventos:

```text
Estado inicial
     ↓
PaymentCreated
     ↓
PaymentApproved
     ↓
Estado atual
```

### Pergunta esperada

> Você utilizaria Event Sourcing obrigatoriamente nesse projeto?

Resposta esperada:

**Não.**

Event Sourcing adiciona complexidade e deve ser utilizado quando o histórico dos eventos for relevante para o domínio, auditoria, reconstrução de estado ou reprocessamento.

---

# 🗄️ Modelo de dados

## payments

```text
id
customer_id
amount
status
created_at
updated_at
```

## payment_events

```text
id
payment_id
event_type
payload
created_at
```

## idempotency_keys

```text
key
payment_id
created_at
```

A chave de idempotência deve possuir uma constraint:

```sql
UNIQUE (key)
```

---

# 🏗️ Arquitetura sugerida

```text
src/main/java
└── com.example.payment
    │
    ├── controller
    │   └── PaymentController
    │
    ├── application
    │   ├── command
    │   │   └── PaymentCommandService
    │   │
    │   └── query
    │       └── PaymentQueryService
    │
    ├── domain
    │   ├── Payment
    │   ├── PaymentStatus
    │   └── PaymentEvent
    │
    ├── repository
    │   ├── PaymentRepository
    │   ├── PaymentEventRepository
    │   └── IdempotencyRepository
    │
    ├── infrastructure
    │   └── PaymentProcessor
    │
    └── exception
        └── GlobalExceptionHandler
```

Não é necessário seguir exatamente essa estrutura.

O importante é manter responsabilidades bem separadas.

---

# 🧪 Testes obrigatórios

## Unitários

Testar pelo menos:

* criação de pagamento;
* pagamento inválido;
* processamento;
* consulta;
* idempotência.

---

## Integração

Testar:

```text
POST /payments
GET /payments/{id}
GET /customers/{customerId}/payments
```

---

# 🚨 Teste de concorrência

Esse é um dos principais testes do desafio.

Execute aproximadamente:

```text
100 requisições simultâneas
```

utilizando a mesma:

```text
Idempotency-Key
```

Resultado esperado:

```text
100 requests
      ↓
1 payment
```

Não podem existir pagamentos duplicados.

---

# 📊 Alta carga

Considere o seguinte cenário:

```text
50.000 requests/segundo
```

Você não precisa necessariamente atingir essa carga no computador local.

Você deve explicar como evoluiria a arquitetura.

Considere:

* Load Balancer
* múltiplas instâncias
* Virtual Threads
* cache
* banco de dados
* connection pool
* filas
* processamento assíncrono
* rate limiting
* backpressure
* timeout
* retry
* circuit breaker
* observabilidade

---

# 🧠 Perguntas da entrevista

Ao finalizar o desafio, você deverá conseguir responder:

### Virtual Threads

1. O que são Virtual Threads?
2. Quando utilizá-las?
3. Quando não utilizá-las?
4. Elas aumentam CPU?
5. Qual a relação entre Virtual Threads e connection pool?

### CQRS

6. O que é CQRS?
7. Por que separar Command e Query?
8. CQRS exige dois bancos?
9. Quando CQRS seria exagero?

### Event Sourcing

10. O que é Event Sourcing?
11. Qual a diferença entre Event Sourcing e simplesmente salvar eventos?
12. Quais vantagens?
13. Quais problemas?
14. Quando você não utilizaria?

### Alta concorrência

15. Como sua API suportaria milhares de requisições simultâneas?
16. Onde estaria o gargalo?
17. Como evitaria duplicidade?
18. Como protegeria o banco?
19. Como trataria uma dependência externa lenta?
20. O que aconteceria se o banco ficasse indisponível?

### Arquitetura

21. Como escalar horizontalmente?
22. Onde colocaria cache?
23. Quando utilizaria Kafka?
24. Quando utilizaria processamento síncrono?
25. Como monitoraria a aplicação?

---

# ⭐ Critérios de avaliação

## Obrigatório

* [ ] Java 21+
* [ ] Spring Boot
* [ ] API REST funcional
* [ ] PostgreSQL
* [ ] Docker Compose
* [ ] CRUD mínimo de pagamentos
* [ ] Idempotência
* [ ] Transações
* [ ] Testes automatizados
* [ ] Virtual Threads
* [ ] Separação Command/Query
* [ ] Processamento assíncrono

## Diferenciais

* [ ] Kafka
* [ ] Redis
* [ ] Testcontainers
* [ ] Micrometer
* [ ] Prometheus
* [ ] Grafana
* [ ] Teste de carga
* [ ] Event Store
* [ ] Circuit Breaker
* [ ] Rate Limiting

---

# 🚀 Entrega mínima esperada

Ao finalizar, o projeto deve permitir:

```text
1. Subir PostgreSQL via Docker
2. Subir a aplicação
3. Criar um pagamento
4. Consultar o pagamento
5. Listar pagamentos
6. Processar pagamento de forma assíncrona
7. Impedir duplicidade através de Idempotency-Key
8. Executar testes
9. Demonstrar concorrência
10. Explicar as decisões arquiteturais
```

---

# 🎯 Desafio principal

Imagine que amanhã o produto informe:

> **"Nossa API passou de 500 para 20.000 requisições por segundo."**

Você deverá ser capaz de explicar:

```text
                 20.000 req/s
                      |
                      ↓
                Load Balancer
                      |
          +-----------+-----------+
          |           |           |
         API         API         API
          |           |           |
          +-----------+-----------+
                      |
              Virtual Threads
                      |
             +--------+--------+
             |                 |
          COMMAND            QUERY
             |                 |
             ↓                 ↓
         Write DB          Read Model
             |
             ↓
           Events
             |
             ↓
        Async Processor
```

E responder:

> **Onde está o gargalo?**

> **Como você descobriria?**

> **O que você escalaria primeiro?**

> **Por que Virtual Threads?**

> **Por que CQRS?**

> **Por que processamento assíncrono?**

> **Onde colocaria cache?**

> **Como impediria duplicidade?**

> **O que acontece se o Kafka/banco ficar indisponível?**

> **Como você monitoraria tudo isso?**

---

# 🏁 Regra do desafio

**Não tente implementar todas as tecnologias possíveis.**

A prioridade é:

```text
API REST
   ↓
Java 21
   ↓
Concorrência
   ↓
Idempotência
   ↓
Virtual Threads
   ↓
CQRS
   ↓
Eventos
   ↓
Processamento assíncrono
```

O objetivo final é demonstrar que você sabe **projetar e justificar uma API Java para alto volume**, e não simplesmente acumular tecnologias no projeto.
