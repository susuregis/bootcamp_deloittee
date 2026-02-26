Aula 06 - Princípios SOLID e Refatoração de Arquitetura

Conteúdo da Aula

Nesta aula foi implementada uma refatoração completa da aplicação Spring Boot (desenvolvida na Aula 05) utilizando os cinco princípios SOLID. A aplicação foi transformada de uma arquitetura monolítica simples para uma arquitetura profissional que demonstra as melhores práticas de engenharia de software.

Sobre o Projeto

Sistema de gerenciamento de peças, fornecedores, estoque e pedidos desenvolvido com Spring Boot 3.2.2 e Java 21, agora implementando todos os princípios SOLID para garantir código maintível, testável e extensível.

Melhorias Implementadas

1. Princípios SOLID Aplicados

Single Responsibility Principle (SRP)
- Controllers: Responsabilidade única de apresentação
- Services: Responsabilidade única de lógica de negócio
- DTOs: Responsabilidade única de transferência de dados entre camadas
- Mappers: Responsabilidade única de conversão entre objetos
- Repositories: Responsabilidade única de acesso aos dados

Open/Closed Principle (OCP)
- Interface GenericService: Aberta para extensão através de implementações específicas, fechada para modificação
- Novos services podem ser adicionados sem alterar código existente
- Padrão extensível para novos repositórios através de GenericRepository

Liskov Substitution Principle (LSP)
- Todos os services implementam o contrato GenericService
- Services são intercambiáveis entre si sem quebrar a aplicação
- Mappers implementam EntityMapper garantindo conformidade de interface

Interface Segregation Principle (ISP)
- GenericService com apenas operações essenciais necessárias
- DTOs enxutos com apenas campos relevantes
- Métodos específicos adicionados em cada service conforme necessário
- EntityMapper com interface mínima focada em conversão

Dependency Inversion Principle (DIP)
- Eliminação de @Autowired em atributos
- Constructor Injection obrigatória em todas as classes
- Dependências explícitas e imutáveis (private final)
- Facilita testes unitários com mock de dependências
- Reduz acoplamento entre componentes

2. Novos Componentes Criados

Interfaces de Contrato

GenericService: Define operações comuns para todos os services
- listarTodas(): Retorna lista de todas as entidades
- buscarPorId(ID): Busca entidade por identificador
- salvar(T): Persiste entidade
- deletar(ID): Remove entidade por ID
- existe(ID): Verifica existência de entidade

GenericRepository: Define operações base para repositórios
- Estende JpaRepository com tipagem genérica
- Permite criação de novos repositórios seguindo padrão

EntityMapper: Define contrato para mapeadores
- toDTO(E): Converte entity para DTO
- toEntity(D): Converte DTO para entity
- updateEntityFromDTO(D, E): Atualiza entity com dados do DTO

Data Transfer Objects (DTOs)

PecaDTO
- Campos: id, nome, descricao, preco, codigo, estoqueMinimo, estoqueMaximo, unidadeMedida, peso, fornecedorId
- Validações: @NotBlank, @DecimalMin, @Min
- Separa validações de entrada da entity

FornecedorDTO
- Campos: id, nome, cnpj, email, telefone, endereco, cidade, estado, cep, avaliacao
- Validações: @Email, @Pattern, @NotBlank
- Permite ocultar campos sensíveis da API

EstoqueDTO
- Campos: id, quantidade, pecaId, localizacao
- Validações: @NotNull, @Min
- Reduz quantidade de dados expostos

Mappers

PecaMapper
- Implementa EntityMapper para Peca e PecaDTO
- Gerencia relacionamento com Fornecedor
- Converte entre estruturas de forma centralizada

FornecedorMapper
- Implementa EntityMapper para Fornecedor e FornecedorDTO
- Mapeia dados de contato e endereço
- Converte dados complexos de forma consistente

EstoqueMapper
- Implementa EntityMapper para Estoque e EstoqueDTO
- Gerencia relacionamento com Peca
- Converte quantidades e localizações

3. Services Refatorados

PecaService
- Implementa GenericService<Peca, Long>
- Constructor Injection de PecaRepository
- Método adicional: buscarPorCodigo(String)

EstoqueService
- Implementa GenericService<Estoque, Long>
- Constructor Injection de EstoqueRepository
- Gerencia quantidade e disponibilidade

FornecedorService
- Implementa GenericService<Fornecedor, Long>
- Constructor Injection de FornecedorRepository
- Método adicional: buscarPorCnpj(String)

MovimentacaoService
- Implementa GenericService<Movimentacao, Long>
- Constructor Injection de MovimentacaoRepository
- Método adicional: buscarPorPeca(Long)

PedidoService
- Implementa GenericService<Pedido, Long>
- Constructor Injection de PedidoRepository
- Método adicional: buscarPorNumero(String)

4. Controllers Refatorados

Boas Práticas Implementadas

Injeção de Dependência via Construtor
- Todas as dependências obrigatórias visíveis no construtor
- Torna dependências imutáveis (private final)
- Facilita testes unitários com mocks
- Código mais testável e seguro

Uso de DTOs em Requisições e Respostas
- Controllers recebem DTOs em @RequestBody
- Controllers retornam DTOs nas respostas
- Separação clara entre camadas (presentation vs business)
- Controle sobre dados expostos na API

Validação de Entrada
- @Valid em parâmetros de POST/PUT
- Validações declarativas em DTOs
- Mensagens de erro claras e padronizadas

Tratamento de Respostas HTTP
- HttpStatus.CREATED para POST
- HttpStatus.OK para operações bem-sucedidas
- HttpStatus.NO_CONTENT para DELETE
- ResponseEntity para controle fino de respostas

Endpoints Principais

Peças

GET /api/pecas
- Descrição: Listar todas as peças
- Resposta: Lista de PecaDTO

GET /api/pecas/{id}
- Descrição: Buscar peça por ID
- Parâmetro: id (Long)
- Resposta: PecaDTO ou 404 Not Found

POST /api/pecas
- Descrição: Criar nova peça
- Body: PecaDTO com validações
- Resposta: PecaDTO criada com status 201

PUT /api/pecas/{id}
- Descrição: Atualizar peça existente
- Body: PecaDTO com validações
- Resposta: PecaDTO atualizada

DELETE /api/pecas/{id}
- Descrição: Deletar peça
- Resposta: 204 No Content

Fornecedores

GET /api/fornecedores
- Descrição: Listar todos os fornecedores
- Resposta: Lista de FornecedorDTO

GET /api/fornecedores/{id}
- Descrição: Buscar fornecedor por ID
- Parâmetro: id (Long)
- Resposta: FornecedorDTO ou 404 Not Found

POST /api/fornecedores
- Descrição: Criar novo fornecedor
- Body: FornecedorDTO com validações
- Resposta: FornecedorDTO criada com status 201

PUT /api/fornecedores/{id}
- Descrição: Atualizar fornecedor existente
- Body: FornecedorDTO com validações
- Resposta: FornecedorDTO atualizada

DELETE /api/fornecedores/{id}
- Descrição: Deletar fornecedor
- Resposta: 204 No Content

Estoque

GET /api/estoques
- Descrição: Listar todo o estoque
- Resposta: Lista de EstoqueDTO

GET /api/estoques/{id}
- Descrição: Buscar estoque por ID
- Parâmetro: id (Long)
- Resposta: EstoqueDTO ou 404 Not Found

POST /api/estoques
- Descrição: Criar novo registro de estoque
- Body: EstoqueDTO com validações
- Resposta: EstoqueDTO criada com status 201

PUT /api/estoques/{id}
- Descrição: Atualizar registro de estoque
- Body: EstoqueDTO com validações
- Resposta: EstoqueDTO atualizada

DELETE /api/estoques/{id}
- Descrição: Deletar registro de estoque
- Resposta: 204 No Content

Movimentações

GET /api/movimentacoes
- Descrição: Listar todas as movimentações
- Resposta: Lista de Movimentacao

GET /api/movimentacoes/{id}
- Descrição: Buscar movimentação por ID
- Parâmetro: id (Long)
- Resposta: Movimentacao ou 404 Not Found

POST /api/movimentacoes
- Descrição: Criar nova movimentação
- Body: Movimentacao
- Resposta: Movimentacao criada com status 201

GET /api/movimentacoes/peca/{pecaId}
- Descrição: Buscar movimentações de uma peça
- Parâmetro: pecaId (Long)
- Resposta: Lista de Movimentacao

Pedidos

GET /api/pedidos
- Descrição: Listar todos os pedidos
- Resposta: Lista de Pedido

GET /api/pedidos/{id}
- Descrição: Buscar pedido por ID
- Parâmetro: id (Long)
- Resposta: Pedido ou 404 Not Found

POST /api/pedidos
- Descrição: Criar novo pedido
- Body: Pedido
- Resposta: Pedido criada com status 201

PUT /api/pedidos/{id}
- Descrição: Atualizar pedido existente
- Body: Pedido
- Resposta: Pedido atualizada

DELETE /api/pedidos/{id}
- Descrição: Deletar pedido
- Resposta: 204 No Content

Como Executar

Pré-requisitos

- Java 21 ou superior
- Maven 3.8.1 ou superior
- Git (opcional, para clonar o repositório)

Executar em Desenvolvimento

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação será iniciada em http://localhost:8080

Executar com Profile Específico

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
mvn spring-boot:run -Dspring-boot.run.profiles=prod
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

Build para Deploy

```bash
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Executar Testes

```bash
mvn test
```

Validação da Compilação

```bash
mvn clean compile
```

Estrutura de Diretórios

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/          (REST Controllers)
│   │   ├── service/             (Business Logic)
│   │   │   ├── GenericService.java (Interface base)
│   │   │   └── *Service.java    (Implementações)
│   │   ├── repository/          (Data Access)
│   │   │   ├── GenericRepository.java (Interface base)
│   │   │   └── *Repository.java (Implementações)
│   │   ├── mapper/              (Entity/DTO Converters)
│   │   │   ├── EntityMapper.java (Interface base)
│   │   │   └── *Mapper.java    (Implementações)
│   │   ├── dto/                 (Data Transfer Objects)
│   │   │   └── *DTO.java       (Modelos)
│   │   ├── model/               (Entity Classes)
│   │   ├── exception/           (Exception Handlers)
│   │   └── config/              (Spring Configuration)
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       ├── application-prod.properties
│       └── application-test.properties
```

Documentação da API

Swagger/OpenAPI

Acesse a documentação interativa em: http://localhost:8080/swagger-ui.html

Principais recursos:
- Exploração interativa de endpoints
- Testar requisições e respostas
- Visualizar modelos de dados
- Entender estrutura de respostas

API Docs JSON

A documentação em formato JSON disponível em: http://localhost:8080/api-docs

Útil para integração com ferramentas como Postman

Console do Banco de Dados

Acesse o console H2 em: http://localhost:8080/h2-console

Credenciais:
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: (deixar em branco)

Documentação Adicional

Para informações detalhadas sobre a implementação SOLID:

SOLID_REFACTORING.md - Documentação técnica aprofundada dos princípios SOLID implementados

SOLID_SUMMARY.md - Resumo visual dos princípios com diagramas e exemplos

SOLID_GUIDE.md - Guia prático com exemplos de como adicionar novas entidades

INDEX_DOCUMENTATION.md - Índice completo e navegação entre documentações

Arquitetura e Design Patterns

Camada de Apresentação (Controllers)

Responsabilidade: Receber requisições HTTP e retornar respostas
Componentes: REST Controllers
Padrão: Constructor Injection de Services
Validação: DTOs com Bean Validation

Camada de Negócio (Services)

Responsabilidade: Implementar lógica de negócio
Componentes: Services implementando GenericService
Padrão: Constructor Injection de Repositories
Extensibilidade: Novos services estendem GenericService

Camada de Mapeamento (Mappers)

Responsabilidade: Converter entre Entity e DTO
Componentes: Mappers implementando EntityMapper
Padrão: Spring Components com Constructor Injection
Benefício: Separação clara entre camadas

Camada de Acesso a Dados (Repositories)

Responsabilidade: Operações CRUD e consultas customizadas
Componentes: Repositories estendendo GenericRepository
Padrão: Spring Data JPA
Dados: Banco H2 para desenvolvimento

Padrões de Design Utilizados

Repository Pattern: Abstração do acesso a dados
Service Layer: Isolamento de lógica de negócio
Data Transfer Object (DTO): Separação entre camadas
Mapper Pattern: Conversão entre objetos
Dependency Injection: Inversão de controle
SOLID: Cinco princípios de design orientado a objetos

