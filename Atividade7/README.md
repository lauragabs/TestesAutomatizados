# Sistema de Gerenciamento de Clientes

Este projeto implementa um sistema de gerenciamento de clientes usando Spring Boot, com testes de integração da camada web utilizando MockMVC.

## Funcionalidades

- Cadastro de novos clientes
- Consulta paginada de clientes
- Consulta de cliente por ID
- Atualização de dados de clientes
- Remoção de clientes
- Filtro de clientes por renda exata
- Filtro de clientes por renda maior que um valor

---

## Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (para testes)
- JUnit 5 & MockMvc (para testes automatizados)

---

## Dados de Teste

O arquivo `import.sql` contém registros de clientes para teste, incluindo:

- Conceição Evaristo (income: 1500.0)
- Lázaro Ramos (income: 2500.0)
- Clarice Lispector (income: 3800.0)
- E outros...

Estes dados são carregados automaticamente no banco H2 durante os testes de integração.

---

## Endpoints

### Listar todos os clientes (paginação)

```
GET /clients
```

**Parâmetros de query opcionais:**

- `page` (padrão: 0)
- `linesPerPage` (padrão: 12)
- `direction` (ASC ou DESC, padrão: ASC)
- `orderBy` (campo para ordenação, padrão: name)

---

### Buscar cliente por ID

```
GET /clients/{id}
```

---

### Cadastrar novo cliente

```
POST /clients
```

**Body:**

```json
{
  "name": "Maria Silva",
  "cpf": "12345678900",
  "income": 2500.0,
  "birthDate": "1990-05-15T10:30:00Z",
  "children": 1
}
```

---

### Atualizar cliente existente

```
PUT /clients/{id}
```

**Body:**  
Mesmo formato do POST.

---

### Remover cliente

```
DELETE /clients/{id}
```

---

### Buscar clientes por renda exata (paginação)

```
GET /clients/income?income={valor}
```

**Parâmetros de query opcionais:**

- `page`, `linesPerPage`, `direction`, `orderBy` (iguais ao endpoint de listagem)

---

### Buscar clientes com renda maior que um valor (paginação)

```
GET /clients/incomeGreaterThan?income={valor}
```

**Parâmetros de query opcionais:**

- `page`, `linesPerPage`, `direction`, `orderBy` (iguais ao endpoint de listagem)
