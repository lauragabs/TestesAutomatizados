# **Testes Automatizados - IFTM**

As atividades têm como foco a aplicação de testes unitários e de integração, empregando ferramentas como JUnit e Mockito para consolidar boas práticas de desenvolvimento orientado a testes.

## Estrutura do Repositório

Cada subpasta representa uma atividade individual, contendo sua respectiva implementação completa: código-fonte, testes automatizados e, quando aplicável, documentação complementar e relatórios de erro.

    TestesAutomatizados/
    ├── README.md
    ├── Atividade2/
    ├── Atividade3/
    ├── Atividade4e5/

### **Tabela de Resumo das Atividades**

| Atividade | Tecnologia      | Testes             | Conceitos Abordados              |
| --------- | --------------- | ------------------ | -------------------------------- |
| 2         | JUnit           | Unitários          | Operações aritméticas, exceções  |
| 3         | JUnit           | Unitários          | Regras de negócio, TDD           |
| 4         | Spring Data JPA | Integração         | Query Methods, @Query, filtragem |
| 5         | Mockito         | Unitários mockados | Mocks, verificação de chamadas   |

## Descrição das Atividades

### **Atividade 2 - Testes em Classe Calculadora (JUnit)**

- Descrição: Desenvolver testes unitários para a classe `Calculadora`, fornecida pelo professor, cobrindo construtores, operações aritméticas e tratamento de exceções.

- Objetivo: Garantir o comportamento esperado dos métodos `somar`, `subtrair`, `multiplicar`, `dividir`, `exponenciar` e `zerarMemoria`, além de documentar os casos de uso e relatar erros identificados.

### **Atividade 3 - Testes com JUnit (Funcionário e Terceirizado)**

- Descrição: Implementar testes unitários para as classes `Funcionario` e `FuncionarioTerceirizado`, verificando o cumprimento das regras de negócio relacionadas a pagamento, carga horária, valor por hora e despesas adicionais.

- Objetivo: Aplicar a abordagem de TDD para assegurar que todos os métodos, construtores e setters estejam em conformidade com as regras de negócio estabelecidas.

### **Atividade 4 - Testes em JPA Repository**

- Utiliza o projeto [Cliente Teste Modelo](https://github.com/brunoqp78/cliente-teste-modelo.git) como base

- Descrição: Implementar testes na classe `ClientRepositoryTests` utilizando o projeto base com Spring Data JPA.

- Objetivo: Criar e testar métodos personalizados no `ClientRepository` para buscas por nome, parte do nome, salário e data de nascimento, utilizando `QueryMethods` e expressões com `@Query` (quando necessário).

### **Atividade 5 - Testes de Services com Mockito**

- Descrição: Implementar testes unitários na classe `ClientServiceTests` utilizando Mockito, validando os principais comportamentos dos métodos de serviço.

- Objetivo: Assegurar o retorno correto de dados e o tratamento adequado de exceções esperadas dos métodos `delete`, `findAllPaged`, `findByIncome`, `findById`, `update` e `insert`, além de verificar as interações com o repositório.
