
## Aula 05 - Spring Boot e Boas Práticas

### Conteúdo da Aula
Nesta aula foi introduzido o **Spring Boot** ao projeto, transformando uma aplicação Java pura (Aula 04) em uma aplicação Spring Boot completa com:
- Configuração automática
- Injeção de dependências
- REST API
- JPA/Hibernate integrado
- Perfis de ambiente (dev, test, prod)
- Validações
- Documentação automática (Swagger)

## Sobre o Projeto

Sistema de gerenciamento de peças, fornecedores, estoque e pedidos desenvolvido com Spring Boot 3.2.2 e Java 21.

## Melhorias Implementadas

### 1. **Dependências Adicionadas**

#### Validação
- **Spring Boot Starter Validation**: Validação de beans com anotações JSR-303/JSR-380


#### Documentação API
- **Springdoc OpenAPI (Swagger)**: Documentação interativa da API
- Acesso: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/api-docs`


**Benefícios:**
- Validação de entrada com Bean Validation
- Controle sobre dados expostos na API
- Melhor versionamento da API
- Segurança (não expor entidades diretamente)

### 3. **Controllers Refatorados**

#### Boas Práticas Implementadas:

**Injeção de Dependência via Construtor** (com `@RequiredArgsConstructor`)
- Imutabilidade das dependências
- Facilita testes unitários
- Torna dependências obrigatórias explícitas


**Documentação Swagger Completa**
- `@Tag`: Agrupamento de endpoints
- `@Operation`: Descrição de operações
- `@ApiResponses`: Documentação de respostas
- `@Parameter`: Descrição de parâmetros

```

**Tratamento de Erros**
- Exceções customizadas (`ResourceNotFoundException`)
- Validações de negócio
- Mensagens claras de erro


## Como Executar

### Pré-requisitos
- Java 21
- Maven

### Executar em Desenvolvimento
```bash
mvn spring-boot:run
```

### Executar com Profile Específico
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Build
```bash
mvn clean package
```

## Endpoints Principais

### Peças
- `GET /api/pecas` - Listar todas as peças
- `GET /api/pecas/{id}` - Buscar peça por ID
- `GET /api/pecas/codigo/{codigo}` - Buscar peça por código
- `POST /api/pecas` - Criar nova peça
- `PUT /api/pecas/{id}` - Atualizar peça
- `DELETE /api/pecas/{id}` - Deletar peça

### Fornecedores
- `GET /api/fornecedores` - Listar todos os fornecedores
- `GET /api/fornecedores/{id}` - Buscar fornecedor por ID
- `POST /api/fornecedores` - Criar novo fornecedor
- `PUT /api/fornecedores/{id}` - Atualizar fornecedor
- `DELETE /api/fornecedores/{id}` - Deletar fornecedor

### Pedidos
- `GET /api/pedidos` - Listar todos os pedidos
- `GET /api/pedidos/{id}` - Buscar pedido por ID
- `POST /api/pedidos` - Criar novo pedido
- `PUT /api/pedidos/{id}` - Atualizar pedido
- `DELETE /api/pedidos/{id}` - Deletar pedido

## Documentação da API

Acesse a documentação interativa em: `http://localhost:8080/swagger-ui.html`

## Console H2

Acesse o console do banco de dados em: `http://localhost:8080/h2-console`
