# Bootcamp Java - Sistema de Gestão Completo

## Índice

1. [Visão Geral](#visão-geral)
2. [Estrutura do Repositório](#estrutura-do-repositório)
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)
4. [Evolução do Projeto](#evolução-do-projeto)
5. [Como Executar o Projeto Final](#como-executar-o-projeto-final)
6. [Conteúdo das Aulas](#conteúdo-das-aulas)
7. [Endpoints da API](#endpoints-da-api)
8. [Perfis de Ambiente](#perfis-de-ambiente)

---

## Visão Geral

Este repositório documenta o desenvolvimento completo de um **Sistema de Gestão de Peças, Fornecedores, Produtos e Estoque**, desde os conceitos básicos de Java até uma aplicação Full Stack profissional com backend Spring Boot e frontend Bootstrap.

O projeto foi desenvolvido ao longo de 7 aulas, evoluindo progressivamente de uma aplicação Java pura para uma aplicação web moderna com arquitetura em camadas, princípios SOLID, API REST documentada e interface gráfica responsiva.

### Funcionalidades Principais

- Gestão completa de Produtos, Categorias, Fornecedores e Peças
- API REST com documentação Swagger/OpenAPI
- Validação de dados com Bean Validation
- Relacionamentos JPA complexos (OneToMany, ManyToOne, ManyToMany)
- Sistema de logging
- Perfis de ambiente (dev, test, prod)
- Interface web responsiva com Bootstrap 5
- Operações CRUD completas via frontend

---

## Estrutura do Repositório

```
bootcamp_java/
│
├── README.md                          # Este arquivo - documentação principal
│
├── Aula01/                            # Fundamentos de Java
│   └── Aula01/
│       ├── README.md
│       └── demo/                      # Projeto Java puro
│           ├── pom.xml
│           ├── queries-h2.sql
│           └── src/
│
├── Aula02/                            # Git e GitHub
│   └── Aula02/
│       ├── README.md
│       └── demo/                      # Hibernate + JPA
│           ├── pom.xml
│           └── src/
│
├── Aula03/                            # POO (Programação Orientada a Objetos)
│   └── Aula03/
│       ├── README.md
│       └── demo/                      # Classes e relacionamentos
│           ├── pom.xml
│           └── src/
│
├── Aula04/                            # JPA e Hibernate avançado
│   └── Aula04/
│       ├── README.md
│       └── demo/                      # EntityManager e JPQL
│           ├── pom.xml
│           └── src/
│
├── Aula05/                            # Spring Boot
│   └── demo/
│       ├── README.md
│       ├── pom.xml
│       ├── data/                      # Banco H2
│       ├── logs/                      # Arquivos de log
│       └── src/
│           ├── main/
│           │   ├── java/com/example/demo/
│           │   │   ├── controller/    # REST Controllers
│           │   │   ├── dto/           # Data Transfer Objects
│           │   │   ├── entity/        # Entidades JPA
│           │   │   ├── repository/    # Spring Data Repositories
│           │   │   └── service/       # Camada de negócio
│           │   └── resources/
│           │       └── application*.properties
│           └── test/
│
├── Aula06/                            # SOLID e Clean Architecture
│   └── demo/
│       ├── README.md
│       ├── pom.xml
│       ├── data/
│       ├── logs/
│       └── src/
│           └── main/java/com/example/demo/
│               ├── controller/        # Controllers refatorados
│               ├── dto/               # DTOs otimizados
│               ├── entity/            # Entidades
│               ├── mapper/            # Conversores Entity <-> DTO
│               ├── repository/        # Repositórios
│               └── service/
│                   ├── interfaces/    # Contratos de serviço
│                   └── impl/          # Implementações
│
└── Aula07/                            # Frontend + Backend Integrado
    ├── README.md
    ├── demo/                          # Backend 
    
    │   ├── pom.xml
    │   ├── data/
    │   ├── logs/
    │   └── src/
    └── frontend/                      # Interface web
        ├── index.html                 # Dashboard principal
        ├── css/
        │   └── style.css              # Estilos customizados
        ├── js/
        │   ├── api.js                 # Configuração da API
        │   ├── produtos.js            # Lógica de produtos
        │   ├── categorias.js          # Lógica de categorias
        │   ├── fornecedores.js        # Lógica de fornecedores
        │   └── pecas.js               # Lógica de peças
        └── pages/
            ├── produtos.html          # CRUD de produtos
            ├── categorias.html        # CRUD de categorias
            ├── fornecedores.html      # CRUD de fornecedores
            └── pecas.html             # CRUD de peças
```

---

## Tecnologias Utilizadas

### Backend

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 | Linguagem de programação principal |
| **Spring Boot** | 3.2.2 | Framework principal para desenvolvimento |
| **Spring Data JPA** | 3.2.2 | Abstração para acesso a dados |
| **Hibernate** | 6.4.1 | Implementação JPA para ORM |
| **H2 Database** | 2.2.224 | Banco de dados em memória/arquivo |
| **Maven** | 3.9+ | Gerenciador de dependências e build |
| **Lombok** | 1.18.30 | Redução de boilerplate code |
| **Bean Validation** | 3.0.2 | Validação de dados |
| **Springdoc OpenAPI** | 2.3.0 | Documentação automática da API |
| **SLF4J + Logback** | 2.0.11 | Sistema de logging |

### Frontend

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **HTML5** | - | Estrutura das páginas |
| **CSS3** | - | Estilização customizada |
| **JavaScript** | ES6+ | Lógica e requisições AJAX |
| **Bootstrap** | 5.3.2 | Framework CSS responsivo |
| **Bootstrap Icons** | 1.11.2 | Ícones vetoriais |
| **Fetch API** | - | Requisições HTTP assíncronas |

### Ferramentas de Desenvolvimento

| Ferramenta | Uso |
|------------|-----|
| **Git** | Controle de versão |
| **GitHub** | Repositório remoto e colaboração |
| **IntelliJ IDEA / VSCode** | IDEs recomendadas |
| **Postman** | Testes de API |
| **Python HTTP Server** | Servidor local para frontend |

---

## Evolução do Projeto

### Cronograma de Desenvolvimento

| Aula | Tema | Principais Implementações |
|------|------|---------------------------|
| **01** | Fundamentos Java | Sintaxe básica, tipos primitivos, controle de fluxo, arrays, métodos |
| **02** | Git e GitHub | Versionamento, branches, commits, pull requests, colaboração |
| **03** | POO | Classes, objetos, encapsulamento, herança, polimorfismo, abstrações |
| **04** | JPA/Hibernate | Entidades, EntityManager, JPQL, relacionamentos, persistence.xml |
| **05** | Spring Boot | REST API, Spring Data JPA, validações, Swagger, profiles |
| **06** | SOLID | Refatoração arquitetural, DTOs, Mappers, interfaces, injeção de dependência |
| **07** | Frontend | Dashboard, CRUDs completos, integração com API, Bootstrap UI |

### Principais Melhorias por Versão

#### Aula 01-03: Base Java
- Implementação de entidades básicas
- Persistência com Hibernate puro
- Relacionamentos simples OneToMany e ManyToOne

#### Aula 04: JPA Avançado
- EntityManager e transactions
- JPQL para consultas customizadas
- Repositórios manuais com padrão DAO

#### Aula 05: Spring Boot
- Migração para Spring Boot
- Controllers REST com ResponseEntity
- Spring Data JPA (eliminação de código boilerplate)
- DTOs para separação de camadas
- Swagger para documentação automática
- Sistema de logging configurável
- Perfis de ambiente (dev/test/prod)

#### Aula 06: Arquitetura Profissional
- Aplicação completa dos princípios SOLID
- Interface GenericService para padronização
- Mappers dedicados (Entity ↔ DTO)
- Constructor Injection obrigatória
- Código altamente testável e manutenível
- Separação clara de responsabilidades

#### Aula 07: Full Stack
- Interface web moderna e responsiva
- Operações CRUD completas via frontend
- Comunicação assíncrona com Fetch API
- Validação de formulários no cliente
- Feedback visual de operações
- Design profissional com Bootstrap 5

---

## Como Executar o Projeto Final

### Pré-requisitos

Certifique-se de ter instalado:

- **Java Development Kit (JDK) 21** ou superior
- **Maven 3.9+** para build do backend
- **Python 3.x** (para servir o frontend) ou qualquer servidor HTTP local

### Passo 1: Executar o Backend

#### Opção A: Usando Maven Wrapper (Recomendado)

```bash
# Navegue até o diretório do backend da Aula07
cd Aula07/demo

# Execute o projeto
./mvnw clean spring-boot:run
```

#### Opção B: Usando Maven instalado

```bash
cd Aula07/demo
mvn clean spring-boot:run
```

#### Opção C: Gerando JAR executável

```bash
cd Aula07/demo
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

**O backend estará disponível em:**
- API REST: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs JSON: `http://localhost:8080/v3/api-docs`
- Console H2: `http://localhost:8080/h2-console`

### Passo 2: Executar o Frontend

#### Opção A: Usando Python HTTP Server

```bash
# Em um novo terminal, navegue até o frontend
cd Aula07/frontend

# Inicie o servidor HTTP
python -m http.server 8000
```

#### Opção B: Usando Node.js http-server

```bash
# Instale http-server globalmente (apenas uma vez)
npm install -g http-server

# Navegue até o frontend e execute
cd Aula07/frontend
http-server -p 8000
```

#### Opção C: Usando extensão Live Server do VSCode

1. Instale a extensão **Live Server** no VSCode
2. Abra o arquivo `Aula07/frontend/index.html`
3. Clique com botão direito e selecione "Open with Live Server"

**O frontend estará disponível em:**
- Dashboard: `http://localhost:8000/index.html`
- Produtos: `http://localhost:8000/pages/produtos.html`
- Categorias: `http://localhost:8000/pages/categorias.html`
- Fornecedores: `http://localhost:8000/pages/fornecedores.html`
- Peças: `http://localhost:8000/pages/pecas.html`

### Passo 3: Testar a Aplicação

1. Acesse o dashboard em `http://localhost:8000`
2. Navegue pelas diferentes seções usando o menu lateral
3. Experimente criar, editar e excluir registros
4. Verifique a integração com a API através do console do navegador (F12)

### Configuração do Banco de Dados H2

Para acessar o console do H2 e visualizar os dados:

1. Acesse: `http://localhost:8080/h2-console`
2. Use as seguintes credenciais:
   - **JDBC URL**: `jdbc:h2:file:./data/demodb`
   - **User Name**: `sa`
   - **Password**: (deixe em branco)

---

## Conteúdo das Aulas

### Aula 01 - Fundamentos de Java

**Objetivos:**
- Compreender a sintaxe básica da linguagem Java
- Trabalhar com tipos primitivos e controle de fluxo
- Criar e utilizar métodos e arrays
- Compilar e executar programas Java

**Tópicos Abordados:**
- Variáveis e tipos de dados (int, double, boolean, String)
- Operadores aritméticos, relacionais e lógicos
- Estruturas condicionais (if/else, switch)
- Estruturas de repetição (for, while, do-while)
- Arrays unidimensionais e multidimensionais
- Métodos estáticos e não-estáticos
- Compilação (javac) e execução (java)

**Implementação:**
- Projeto Java puro sem frameworks
- Primeiras classes de entidades
- Persistência básica com Hibernate

[Ver documentação completa](Aula01/Aula01/README.md)

---

### Aula 02 - Git e GitHub

**Objetivos:**
- Dominar controle de versão com Git
- Colaborar em equipe usando GitHub
- Aplicar boas práticas de versionamento

**Tópicos Abordados:**
- Inicialização de repositórios (`git init`)
- Commits e histórico (`git commit`, `git log`)
- Branches e merge (`git branch`, `git merge`)
- Repositórios remotos (`git push`, `git pull`)
- Pull Requests e Code Review
- Resolução de conflitos
- Gitflow e convenções de commit

**Implementação:**
- Mesmo projeto da Aula 01 com controle de versão
- Prática de branching e merging
- Colaboração via GitHub

[Ver documentação completa](Aula02/Aula02/README.md)

---

### Aula 03 - Programação Orientada a Objetos

**Objetivos:**
- Aplicar os pilares da POO em Java
- Criar hierarquias de classes
- Implementar polimorfismo e abstração

**Tópicos Abordados:**
- Classes e objetos
- Encapsulamento (getters/setters, modificadores de acesso)
- Herança (extends, super)
- Polimorfismo (sobrescrita de métodos, casting)
- Classes abstratas e interfaces
- Composição vs Herança
- Princípios básicos de design

**Implementação:**
- Expansão do modelo de domínio
- Novas entidades: Produto, Categoria, Fornecedor, Peça
- Relacionamentos entre classes
- Uso extensivo de encapsulamento

[Ver documentação completa](Aula03/Aula03/README.md)

---

### Aula 04 - JPA e Hibernate

**Objetivos:**
- Mapear objetos Java para tabelas relacionais
- Configurar e utilizar JPA/Hibernate
- Implementar relacionamentos complexos

**Tópicos Abordados:**
- Anotações JPA (@Entity, @Id, @Column)
- Relacionamentos (@OneToMany, @ManyToOne, @ManyToMany)
- EntityManager e Persistence Context
- JPQL (Java Persistence Query Language)
- Configuração via persistence.xml
- Transações e ciclo de vida de entidades
- Lazy vs Eager loading
- Cascade operations

**Implementação:**
- EntityManager puro (sem Spring)
- Repositórios manuais com padrão DAO
- Queries JPQL customizadas
- Relacionamentos bidirecionais

[Ver documentação completa](Aula04/Aula04/README.md)

---

### Aula 05 - Spring Boot e Boas Práticas

**Objetivos:**
- Migrar para Spring Boot
- Criar uma API REST profissional
- Implementar validações e documentação

**Tópicos Abordados:**
- Configuração automática do Spring Boot
- Injeção de dependências (@Autowired, @Component, @Service)
- Spring Data JPA (redução de código boilerplate)
- REST Controllers (@RestController, @RequestMapping)
- DTOs (Data Transfer Objects)
- Bean Validation (@Valid, @NotNull, @Size)
- Swagger/OpenAPI para documentação
- Profiles de ambiente (application-{profile}.properties)
- Sistema de logging (SLF4J + Logback)
- Tratamento de exceções

**Implementação:**
- Controllers REST com operações CRUD
- DTOs separados por entidade
- Validações de entrada
- Swagger UI interativo
- Configurações específicas por ambiente
- Logs estruturados em arquivo

**Endpoints Criados:**
- `/api/produtos` - Gestão de produtos
- `/api/categorias` - Gestão de categorias
- `/api/fornecedores` - Gestão de fornecedores
- `/api/pecas` - Gestão de peças

[Ver documentação completa](Aula05/demo/README.md)

---

### Aula 06 - Princípios SOLID

**Objetivos:**
- Aplicar todos os princípios SOLID
- Refatorar para arquitetura em camadas
- Implementar padrões de design profissionais

**Tópicos Abordados:**

#### Single Responsibility Principle (SRP)
- Separação clara de responsabilidades
- Controllers apenas para apresentação
- Services apenas para lógica de negócio
- Repositories apenas para acesso a dados
- DTOs apenas para transferência de dados
- Mappers apenas para conversão

#### Open/Closed Principle (OCP)
- Interfaces genéricas extensíveis
- Novos serviços sem modificar código existente
- Polimorfismo para extensibilidade

#### Liskov Substitution Principle (LSP)
- Services intercambiáveis
- Conformidade de contratos
- Substituição sem quebrar funcionalidade

#### Interface Segregation Principle (ISP)
- Interfaces enxutas e focadas
- Apenas métodos necessários
- DTOs mínimos sem campos desnecessários

#### Dependency Inversion Principle (DIP)
- Constructor Injection obrigatória
- Dependências explícitas e imutáveis (final)
- Eliminação de @Autowired em campos
- Facilita mocks em testes

**Implementação:**
- Interface GenericService<T, ID>
- Serviços implementando interface comum
- Mappers dedicados (EntityMapper)
- Constructor Injection em todas as classes
- Código altamente testável

**Componentes Criados:**
```
service/
├── interfaces/
│   ├── GenericService.java
│   ├── ProdutoService.java
│   ├── CategoriaService.java
│   ├── FornecedorService.java
│   └── PecaService.java
└── impl/
    ├── ProdutoServiceImpl.java
    ├── CategoriaServiceImpl.java
    ├── FornecedorServiceImpl.java
    └── PecaServiceImpl.java

mapper/
├── EntityMapper.java (interface)
├── ProdutoMapper.java
├── CategoriaMapper.java
├── FornecedorMapper.java
└── PecaMapper.java
```

[Ver documentação completa](Aula06/demo/README.md)

---

### Aula 07 - Frontend com Bootstrap

**Objetivos:**
- Desenvolver interface web completa
- Integrar frontend com API REST
- Criar experiência de usuário moderna

**Tópicos Abordados:**
- HTML5 semântico
- CSS3 (Flexbox, Grid, animações)
- JavaScript ES6+ (arrow functions, async/await, destructuring)
- Bootstrap 5 (grid system, componentes, utilitários)
- Fetch API para requisições HTTP
- Manipulação do DOM
- Event Listeners
- Validação de formulários no cliente
- Feedback visual (modals, alerts, spinners)
- Design responsivo

**Funcionalidades do Frontend:**
- Dashboard com cards informativos
- Menu lateral responsivo com navegação
- Tabelas com listagem de dados
- Formulários de criação/edição em modals
- Botões de ação (editar, excluir)
- Confirmação de exclusão
- Mensagens de sucesso/erro
- Validação de campos obrigatórios
- Atualização automática de listas

**Estrutura do Código Frontend:**

```javascript
// api.js - Configuração centralizada
const API_BASE_URL = 'http://localhost:8080/api';

// Funções reutilizáveis
async function apiGet(endpoint) { ... }
async function apiPost(endpoint, data) { ... }
async function apiPut(endpoint, data) { ... }
async function apiDelete(endpoint) { ... }

// produtos.js - Lógica específica de produtos
async function carregarProdutos() { ... }
async function salvarProduto() { ... }
async function editarProduto(id) { ... }
async function excluirProduto(id) { ... }
```

**Páginas Implementadas:**
1. **index.html** - Dashboard principal com resumo
2. **produtos.html** - CRUD completo de produtos
3. **categorias.html** - CRUD completo de categorias
4. **fornecedores.html** - CRUD completo de fornecedores
5. **pecas.html** - CRUD completo de peças

[Ver documentação completa](Aula07/README.md)

---

## Endpoints da API

### Produtos

| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/api/produtos` | Lista todos os produtos | - | Array de ProdutoDTO |
| GET | `/api/produtos/{id}` | Busca produto por ID | - | ProdutoDTO |
| POST | `/api/produtos` | Cria novo produto | ProdutoDTO | ProdutoDTO |
| PUT | `/api/produtos/{id}` | Atualiza produto existente | ProdutoDTO | ProdutoDTO |
| DELETE | `/api/produtos/{id}` | Exclui produto | - | 204 No Content |

### Categorias

| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/api/categorias` | Lista todas as categorias | - | Array de CategoriaDTO |
| GET | `/api/categorias/{id}` | Busca categoria por ID | - | CategoriaDTO |
| POST | `/api/categorias` | Cria nova categoria | CategoriaDTO | CategoriaDTO |
| PUT | `/api/categorias/{id}` | Atualiza categoria | CategoriaDTO | CategoriaDTO |
| DELETE | `/api/categorias/{id}` | Exclui categoria | - | 204 No Content |

### Fornecedores

| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/api/fornecedores` | Lista todos os fornecedores | - | Array de FornecedorDTO |
| GET | `/api/fornecedores/{id}` | Busca fornecedor por ID | - | FornecedorDTO |
| POST | `/api/fornecedores` | Cria novo fornecedor | FornecedorDTO | FornecedorDTO |
| PUT | `/api/fornecedores/{id}` | Atualiza fornecedor | FornecedorDTO | FornecedorDTO |
| DELETE | `/api/fornecedores/{id}` | Exclui fornecedor | - | 204 No Content |

### Peças

| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/api/pecas` | Lista todas as peças | - | Array de PecaDTO |
| GET | `/api/pecas/{id}` | Busca peça por ID | - | PecaDTO |
| POST | `/api/pecas` | Cria nova peça | PecaDTO | PecaDTO |
| PUT | `/api/pecas/{id}` | Atualiza peça | PecaDTO | PecaDTO |
| DELETE | `/api/pecas/{id}` | Exclui peça | - | 204 No Content |

### Exemplos de Request Bodies

#### ProdutoDTO
```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 15",
  "preco": 3500.00,
  "categoriaId": 1
}
```

#### CategoriaDTO
```json
{
  "nome": "Eletrônicos",
  "descricao": "Produtos eletrônicos em geral"
}
```

#### FornecedorDTO
```json
{
  "nome": "Tech Supply LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "contato@techsupply.com",
  "telefone": "(11) 98765-4321"
}
```

#### PecaDTO
```json
{
  "nome": "Teclado USB",
  "descricao": "Teclado padrão ABNT2",
  "quantidade": 50,
  "fornecedorId": 1
}
```

---

## Perfis de Ambiente

O projeto suporta três perfis de ambiente configuráveis:

### Development (dev) - Padrão

**Características:**
- Banco H2 em arquivo persistente
- Console H2 habilitado
- SQL mostrado no console
- Formatação SQL habilitada
- Logs em nível DEBUG
- Hot reload automático

**Executar:**
```bash
mvn spring-boot:run
# ou
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

**Configurações (application-dev.properties):**
```properties
spring.datasource.url=jdbc:h2:file:./data/demodb
spring.jpa.show-sql=true
spring.h2.console.enabled=true
logging.level.com.example.demo=DEBUG
```

### Production (prod)

**Características:**
- Banco H2 em arquivo otimizado
- Console H2 desabilitado
- SQL não mostrado
- Logs em nível INFO
- Configurações de segurança

**Executar:**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

**Configurações (application-prod.properties):**
```properties
spring.datasource.url=jdbc:h2:file:./data/proddb
spring.jpa.show-sql=false
spring.h2.console.enabled=false
logging.level.com.example.demo=INFO
```

### Test (test)

**Características:**
- Banco H2 em memória (dados não persistem)
- Recriação automática do schema
- Logs detalhados
- Ideal para testes automatizados

**Executar:**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=test"
```

**Configurações (application-test.properties):**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
logging.level.com.example.demo=TRACE
```

### Tabela Comparativa de Perfis

| Característica | dev | prod | test |
|----------------|-----|------|------|
| Banco de dados | Arquivo | Arquivo | Memória |
| Persistência | Sim | Sim | Não |
| Console H2 | Habilitado | Desabilitado | Habilitado |
| Mostrar SQL | Sim | Não | Sim |
| Nível de log | DEBUG | INFO | TRACE |
| DDL auto | update | validate | create-drop |
| Hot reload | Sim | Não | Sim |

---

## Recursos Adicionais

### Documentação da API (Swagger)

Acesse a documentação interativa da API em: `http://localhost:8080/swagger-ui.html`

**Recursos do Swagger:**
- Listagem de todos os endpoints
- Schemas de Request/Response
- Testes interativos direto no navegador
- Exemplos de uso
- Códigos de status HTTP

### Logs da Aplicação

Os logs são salvos em: `Aula07/demo/logs/application.log`

**Configuração de Logging:**
```properties
logging.file.name=logs/application.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.level.root=INFO
logging.level.com.example.demo=DEBUG
```

### Banco de Dados

**Localização:** `Aula07/demo/data/demodb.mv.db`

**Tabelas criadas automaticamente:**
- `produto`
- `categoria`
- `fornecedor`
- `peca`
- `produto_peca` (tabela de relacionamento)

### Scripts Úteis

#### Limpar e reconstruir o projeto
```bash
cd Aula07/demo
mvn clean install
```

#### Executar testes
```bash
mvn test
```

#### Gerar documentação JavaDoc
```bash
mvn javadoc:javadoc
```

#### Ver dependências
```bash
mvn dependency:tree
```

---

## Próximos Passos Sugeridos

1. **Segurança:**
   - Implementar Spring Security
   - Adicionar autenticação JWT
   - Controle de acesso baseado em roles

2. **Testes:**
   - Testes unitários com JUnit 5
   - Testes de integração
   - Testes de API com RestAssured
   - Cobertura de código com JaCoCo

3. **Deploy:**
   - Containerização com Docker
   - Deploy em cloud (AWS, Azure, Heroku)
   - CI/CD com GitHub Actions

4. **Funcionalidades:**
   - Paginação e ordenação
   - Filtros avançados
   - Exportação de relatórios (PDF, Excel)
   - Upload de imagens de produtos
   - Sistema de notificações

5. **Performance:**
   - Cache com Redis
   - Otimização de queries
   - Índices no banco de dados
   - Compressão de respostas HTTP

---

## Contribuindo

Para contribuir com este projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

