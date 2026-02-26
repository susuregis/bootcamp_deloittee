# Bootcamp Java — Estrutura do repositório

Este repositório contém material das aulas do bootcamp organizado por pastas por aula. Abaixo há um resumo do conteúdo de cada pasta e links para os READMEs específicos de cada aula.

- Aula01
  - Conteúdo: conceitos básicos de Java (sintaxe, tipos, controle de fluxo, métodos, arrays, compilação e execução).
  - Implementações: Java puro com Hibernate e JPA para persistência de dados.
  - Local: [Aula01/Aula01/README.md](Aula01/Aula01/README.md)

- Aula02
  - Conteúdo: controle de versão com Git e colaboração via GitHub (comandos, branches, PRs, boas práticas).
  - Implementações: Java puro com Hibernate e JPA para persistência de dados.
  - Local: [Aula02/Aula02/README.md](Aula02/Aula02/README.md)

- Aula03
  - Conteúdo: Programação Orientada a Objetos — classes, objetos, encapsulamento, herança, polimorfismo e exemplos em Java.
  - Implementações: Adicionadas mais classes com foco em POO. Java puro com Hibernate e JPA para persistência.
  - Local: [Aula03/Aula03/README.md](Aula03/Aula03/README.md)

- Aula04
  - Conteúdo: JPA e Hibernate — anotações de entidade, mapeamento objeto-relacional, relacionamentos e configuração.
  - Implementações: Melhorias no JPA e Hibernate com EntityManager puro, persistence.xml e repositórios manuais usando JPQL.
  - Local: [Aula04/Aula04/README.md](Aula04/Aula04/README.md)

- Aula05
  - Conteúdo: Spring Boot e Boas Práticas — introdução ao Spring Boot, Spring Data e melhores práticas utilizando o Spring.
  - Implementações: Spring Boot com Spring Data JPA, REST API, validação, perfis de ambiente (dev/prod/test) e Swagger.
  - Local: [Aula05/demo/README.md](Aula05/demo/README.md)

- Aula06
  - Conteúdo: SOLID — Princípios de Design (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion).
  - Implementações: Spring Boot com aplicação dos princípios SOLID em camadas de serviço, repositórios e controladores. Padrões de Design e Clean Code.
  - Local: [Aula06/demo/README.md](Aula06/demo/README.md)

- Aula07
  - Conteúdo: Frontend com Bootstrap — desenvolvimento de interface web para consumir a API REST.
  - Implementações: Dashboard e CRUDs completos de Produtos, Categorias, Fornecedores e Peças com HTML5, CSS3, JavaScript e Bootstrap 5. Integração com API via Fetch.
  - Local: [Aula07/README.md](Aula07/README.md)

## Como executar a Aula06

### Pré-requisitos
- Java 21
- Maven 3.9+

### Passos para rodar

1. Navegue até o diretório da Aula05:
```bash
cd Aula05/demo
```

2. Compile e execute o projeto:
```bash
mvn clean spring-boot:run
```

Ou, se preferir apenas compilar e depois rodar:
```bash
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

3. A aplicação estará disponível em:
   - **API**: `http://localhost:8080`
   - **Swagger UI**: `http://localhost:8080/swagger-ui.html`
   - **API Docs**: `http://localhost:8080/v3/api-docs`

### Profiles de ambiente

Para rodar com diferentes perfis:

```bash
# Desenvolvimento (padrão)
mvn clean spring-boot:run

# Produção
mvn clean spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"

# Testes
mvn clean spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=test"
```
