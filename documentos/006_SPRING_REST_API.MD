# 📘 Projeto Spring Boot — API REST Biblioteca

## 💡 Contexto do Projeto

Sistema de gerenciamento de biblioteca utilizando:

- Spring Boot
- REST API
- JPA/Hibernate
- Flyway
- DTO
- Mapper
- Validation
- Paginação
- Postman

---

# 🎯 Objetivo do projeto

Aprender conceitos utilizados em APIs reais de mercado:

- arquitetura em camadas
- separação de responsabilidades
- validação de entrada
- DTO e Mapper
- boas práticas REST
- paginação
- versionamento de banco
- testes de API

---

# 📚 Entidades do sistema

O sistema deve possuir:

- Livro
- Leitor
- Emprestimo
- LivroEmprestimo

---

# 🔗 Relacionamentos

| Relacionamento | Tipo |
|---|---|
| Leitor → Emprestimo | 1:N |
| Emprestimo → Livro | N:N |
| LivroEmprestimo | tabela intermediária |

---

# 🚀 1. Criando o projeto no Spring Initializr

Acesse:

```text
https://start.spring.io
```

---

# ⚙️ Configurações

| Campo | Valor |
|---|---|
| Project | Gradle |
| Language | Java |
| Spring Boot | Última versão |
| Group | com.turma2 |
| Artifact | biblioteca-api |
| Java | 17 |

---

# 📦 2. Dependências do projeto

Adicionar:

| Dependência | Objetivo |
|---|---|
| Spring Web | APIs REST |
| Spring Data JPA | Persistência |
| Validation | Validação automática |
| Flyway | Migrations |
| Lombok | Reduz código |
| H2 Driver | Banco H2 |
| DevTools | Reload automático |
| Spring Boot Test | Testes |

---

# 📦 3. build.gradle

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.turma2'
version = '0.0.1-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // API REST
    implementation 'org.springframework.boot:spring-boot-starter-web'

    // JPA / Hibernate
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    // Validation
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    // Flyway
    implementation 'org.flywaydb:flyway-core'

    // Lombok
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

    // Devtools
    developmentOnly 'org.springframework.boot:spring-boot-devtools'

    // Banco H2
    runtimeOnly 'com.h2database:h2'

    // Testes
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

test {
    useJUnitPlatform()
}
```

---

# 📁 4. Estrutura de pacotes

```text
com.turma2.bibliotecaapi
│
├── controllers
│   ├── request
│   └── response
│
├── services
├── repositories
├── models
├── dtos
├── mappers
├── config
└── exceptions
```

---

# 💡 Explicação das camadas

| Camada | Responsabilidade |
|---|---|
| Controller | Entrada HTTP |
| Request | Entrada da API |
| Response | Saída da API |
| DTO | Comunicação entre camadas |
| Mapper | Conversão de objetos |
| Service | Regra de negócio |
| Repository | Banco de dados |
| Model | Entidade JPA |

---

# ⚙️ 5. application.yml

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:bibliotecadb
    driverClassName: org.h2.Driver
    username: sa
    password:

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: validate

  h2:
    console:
      enabled: true
      path: /h2-console

  flyway:
    enabled: true
```

---

# 🛢️ 6. Flyway

---

# 💡 O que é Flyway?

Ferramenta de versionamento de banco de dados.

---

# 🎯 Objetivo

Automatizar:

- criação de tabelas
- inserts iniciais
- alterações futuras

---

# 📁 Estrutura obrigatória

```text
src/main/resources
│
└── db
    └── migration
        ├── V1__create_tables.sql
        ├── V2__insert_data.sql
        └── V3__alter_table.sql
```

---

# 📌 Regra de nomenclatura

```text
V<versão>__<descrição>.sql
```

---

# ✅ Exemplos

```text
V1__create_tables.sql
V2__insert_livros.sql
V3__add_coluna_email.sql
```

---

# ❌ O que NÃO fazer

```text
🚫 Alterar migration já executada
🚫 Reutilizar número
🚫 Apagar migration antiga
```

---

# 🚀 Como funciona

Ao subir aplicação:

```text
1. Flyway conecta no banco
2. Verifica histórico
3. Executa apenas migrations novas
```

---

# 📚 Exemplo migration

## 📁 V1__create_tables.sql

```sql
CREATE TABLE livros(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(255)
);
```

---

# 📚 Exemplo insert

## 📁 V2__insert_data.sql

```sql
INSERT INTO livros(titulo, autor)
VALUES ('Clean Code', 'Robert Martin');
```

---

# 🧩 7. Models (Entities)

---

# 📌 Livro

```java
@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    @OneToMany(mappedBy = "livro")
    private List<LivroEmprestimo> emprestimos;
}
```

---

# 📌 Leitor

```java
@Entity
@Table(name = "leitores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Email
    private String email;

    @OneToMany(mappedBy = "leitor")
    private List<Emprestimo> emprestimos;
}
```

---

# 📌 Emprestimo

```java
@Entity
@Table(name = "emprestimos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataEmprestimo;

    private Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;
}
```

---

# 📌 LivroEmprestimo

```java
@Entity
@Table(name = "livros_emprestimos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;
}
```

---

# 🗄️ 8. Repository

---

# 💡 O que é Repository?

Camada responsável por:

- salvar
- buscar
- atualizar
- deletar

dados do banco.

---

# 🚀 JpaRepository

```java
public interface LivroRepository
    extends JpaRepository<Livro, Long> {
}
```

---

# ✅ Métodos automáticos

```java
save()
findAll()
findById()
deleteById()
```

---

# 🔍 Query derivada

```java
List<Livro> findByTitulo(String titulo);
```

---

# 💡 O Spring interpreta

```sql
SELECT * FROM livros WHERE titulo = ?
```

---

# 🔥 Native Query

```java
@Query(value = """
SELECT * FROM livros
WHERE autor = ?1
""", nativeQuery = true)
List<Livro> buscarPorAutor(String autor);
```

---

# ⚙️ 9. Service

---

# 💡 O que é Service?

Camada responsável por:

- regras de negócio
- orquestração
- conversões

---

# 🎯 Importante

```text
Controller NÃO acessa repository.
```

---

# 📚 Exemplo

```java
@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }
}
```

---

# 🧠 Explicação didática

```text
Repository → fala com banco

Service → decide o que fazer
```

---

# 📦 10. DTO

---

# 💡 O que é DTO?

DTO significa:

```text
Data Transfer Object
```

---

# 🎯 Objetivo

Transportar dados entre camadas.

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

# 💡 Benefícios

- desacoplamento
- segurança
- organização
- reutilização

---

# 🔄 11. Mapper

---

# 💡 O que é Mapper?

Classe responsável por:

```text
converter objetos
```

---

# 🎯 Conversões

```text
Request → DTO
DTO → Entity
Entity → DTO
DTO → Response
```

---

# 📚 Exemplo

```java
public static LivroDTO toDTO(LivroRequest request)
```

---

# 🧾 12. Request e Response

---

# 💡 Objetivo

Separar:

```text
entrada da API
```

de:

```text
saída da API
```

---

# 📥 Request

```json
{
  "titulo": "Clean Code",
  "autor": "Robert Martin"
}
```

---

# 📤 Response

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert Martin"
}
```

---

# 💡 Nem tudo entra

Usuário NÃO envia:

- id
- relacionamentos
- listas

---

# 💡 Nem tudo sai

API NÃO deve retornar:

- listas gigantes
- relacionamentos infinitos
- dados internos

---

# 🛡️ 13. Validation

---

# 💡 O que é Validation?

Sistema automático de validação.

---

# 🎯 Objetivo

Evitar dados inválidos.

---

# 📚 Principais anotações

| Anotação | Função |
|---|---|
| `@NotBlank` | Texto obrigatório |
| `@NotNull` | Não pode ser nulo |
| `@Size` | Tamanho |
| `@Email` | Validar email |
| `@Min` | Valor mínimo |
| `@Max` | Valor máximo |

---

# 📚 Exemplo

```java
@NotBlank
@Size(min = 3, max = 100)
private String titulo;
```

---

# 🚀 Ativando validation

```java
@PostMapping
public ResponseEntity<?> salvar(
        @RequestBody @Valid LivroRequest request
)
```

---

# ❌ JSON inválido

```json
{
  "titulo": ""
}
```

---

# ✅ Resultado

```http
400 BAD REQUEST
```

---

# 🌐 14. Controllers REST

---

# 📚 Principais anotações

| Anotação | Função |
|---|---|
| `@RestController` | Controller REST |
| `@RequestMapping` | Rota base |
| `@GetMapping` | GET |
| `@PostMapping` | POST |
| `@PutMapping` | PUT |
| `@DeleteMapping` | DELETE |
| `@RequestBody` | Captura JSON |
| `@PathVariable` | Captura URL |
| `@RequestParam` | Captura query params |

---

# 📚 Exemplo

```java
@RestController
@RequestMapping("/livros")
public class LivroController {
}
```

---

# 📄 15. Status HTTP

| Status | Significado |
|---|---|
| 200 | OK |
| 201 | CREATED |
| 204 | NO CONTENT |
| 400 | BAD REQUEST |
| 404 | NOT FOUND |

---

# 📑 16. Paginação

---

# 💡 Objetivo

Evitar retorno gigante de dados.

---

# 📚 Exemplo

```http
GET /livros?page=0&size=5
```

---

# 📚 Pageable

```java
Pageable pageable
```

---

# 📚 Retorno

```java
Page<LivroResponse>
```

---

# 🧪 17. Postman

---

# 💡 Objetivo

Testar APIs REST.

---

# 📌 O que testar?

- POST
- GET
- PUT
- DELETE
- paginação
- validações

---

# 🧪 Test Script

```javascript
pm.test("Status 200", function () {
    pm.response.to.have.status(200);
});
```

---

# 💡 O que é pm ?

```text
Postman API
```

Objeto usado pelo Postman para:

- response
- request
- headers
- variáveis
- autenticação

---

# 🧪 Validar JSON

```javascript
pm.test("Tem titulo", function () {

    const json = pm.response.json();

    pm.expect(json.titulo)
        .to.not.be.null;
});
```

---

# 🚀 18. Rodando aplicação

---

# ▶️ Pela IDE

Abrir classe:

```java
@SpringBootApplication
```

Clicar:

```text
Run
```

---

# ▶️ Pelo terminal

```bash
./gradlew bootRun
```

Windows:

```bash
gradlew.bat bootRun
```

---

# 🗄️ 19. Console H2

Abrir:

```text
http://localhost:8080/h2-console
```

---

# ⚙️ Configuração

| Campo | Valor |
|---|---|
| JDBC URL | jdbc:h2:mem:bibliotecadb |
| User | sa |
| Password | vazio |

---

# 📚 SQL úteis

## Listar tabelas

```sql
SHOW TABLES;
```

---

## Buscar livros

```sql
SELECT * FROM livros;
```

---

## Buscar leitores

```sql
SELECT * FROM leitores;
```

---

# 💡 Dicas importantes

```text
✔ H2 em memória apaga ao desligar
✔ Flyway cria banco automaticamente
✔ Nunca alterar migration antiga
✔ DTO evita exposição de entidades
✔ Mapper evita repetição
✔ Validation evita IF manual
```

---

# 🏁 Objetivo final

Construir uma API REST semelhante às utilizadas no mercado:

- organizada
- desacoplada
- escalável
- validada
- reutilizável
- padronizada
