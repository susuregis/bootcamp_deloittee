# Aula 04 — JPA e Hibernate

## Visão geral
Nesta aula foram estudados os fundamentos de JPA (Java Persistence API) e Hibernate como implementador de JPA, com foco em:
- Uso de anotações para mapeamento objeto-relacional.
- Definição de entidades com `@Entity` e gerenciamento da estrutura do banco via JPA/Hibernate.
- Principais relacionamentos e configurações para persistência automática.

## Objetivos de aprendizagem
- Compreender o papel da JPA e do Hibernate na persistência de dados em aplicações Java.
- Aprender a usar anotações para mapear classes Java para tabelas do banco de dados.
- Construir a estrutura do banco (tabelas, chaves, relacionamentos) através de entidades JPA.
- Configurar o provedor JPA (`spring.jpa.*` / `hibernate.*`) e usar um banco em memória (H2) para testes.

## Conceitos principais
- JPA (Java Persistence API): especificação Java para mapeamento objeto-relacional e gerenciamento de entidades.
- Hibernate: implementação popular de JPA que fornece mecanismos de ORM, caching e geração de schema.

### Anotações essenciais
- `@Entity` — marca uma classe como entidade persistente.
- `@Table(name = "nome")` — (opcional) define o nome da tabela no banco.
- `@Id` — indica a chave primária da entidade.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` — estratégia de geração de chave.
- `@Column(name = "coluna", nullable = false, length = 100)` — mapeia campos para colunas com propriedades.

### Relacionamentos
- `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany` — definem cardinalidade entre entidades.
- `@JoinColumn` — especifica a coluna que representa a FK no relacionamento.
- `cascade` (CascadeType.PERSIST, MERGE, REMOVE, etc.) — define propagação de operações.
- `fetch` (FetchType.EAGER, LAZY) — define quando carregar entidades relacionadas.

## Estrutura do banco com JPA e Hibernate
- As classes anotadas com `@Entity` são transformadas em tabelas pelo provedor (quando configurado para auto DDL).
- Relacionamentos entre entidades geram chaves estrangeiras e tabelas intermediárias conforme necessário.
- Hibernate pode gerar o esquema automaticamente (`spring.jpa.hibernate.ddl-auto = create|update|validate|none`).

## Exemplo mínimo de entidade
```java
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // getters e setters
}
```

## Exemplo de configuração (H2 + Spring Boot)
```properties
spring.datasource.url=jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
# Aula 04 — JPA e Hibernate

## Visão geral
Nesta aula foram estudados os fundamentos de JPA (Java Persistence API) e Hibernate como implementador de JPA, com foco em:
- Uso de anotações para mapeamento objeto-relacional.
- Definição de entidades com `@Entity` e gerenciamento da estrutura do banco via JPA/Hibernate.
- Principais relacionamentos e configurações para persistência automática.

## Objetivos de aprendizagem
- Compreender o papel da JPA e do Hibernate na persistência de dados em aplicações Java.
- Aprender a usar anotações para mapear classes Java para tabelas do banco de dados.
- Construir a estrutura do banco (tabelas, chaves, relacionamentos) através de entidades JPA.
- Configurar o provedor JPA (`spring.jpa.*` / `hibernate.*`) e usar um banco em memória (H2) para testes.

## Conceitos principais
- JPA (Java Persistence API): especificação Java para mapeamento objeto-relacional e gerenciamento de entidades.
- Hibernate: implementação popular de JPA que fornece mecanismos de ORM, caching e geração de schema.

### Anotações essenciais
- `@Entity` — marca uma classe como entidade persistente.
- `@Table(name = "nome")` — (opcional) define o nome da tabela no banco.
- `@Id` — indica a chave primária da entidade.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` — estratégia de geração de chave.
- `@Column(name = "coluna", nullable = false, length = 100)` — mapeia campos para colunas com propriedades.

### Relacionamentos
- `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany` — definem cardinalidade entre entidades.
- `@JoinColumn` — especifica a coluna que representa a FK no relacionamento.
- `cascade` (CascadeType.PERSIST, MERGE, REMOVE, etc.) — define propagação de operações.
- `fetch` (FetchType.EAGER, LAZY) — define quando carregar entidades relacionadas.

## Estrutura do banco com JPA e Hibernate
- As classes anotadas com `@Entity` são transformadas em tabelas pelo provedor (quando configurado para auto DDL).
- Relacionamentos entre entidades geram chaves estrangeiras e tabelas intermediárias conforme necessário.
- Hibernate pode gerar o esquema automaticamente (`spring.jpa.hibernate.ddl-auto = create|update|validate|none`).


## Exemplo (código do projeto)
A seguir há exemplos das entidades presentes no projeto: `Categoria` (arquivo: `demo/src/main/java/com/example/demo/model`).

`Categoria.java`
```java
package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Boolean ativa = true;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        produto.setCategoria(this);
    }

    // getters, setters, equals, hashCode, toString
}
```


## Exemplo de configuração (H2 + Spring Boot)
```properties
spring.datasource.url=jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```
- `spring.jpa.hibernate.ddl-auto=update` permite que o Hibernate ajuste o schema conforme as entidades durante o desenvolvimento.
- Ativar `spring.h2.console.enabled=true` facilita inspeção manual do banco H2 em `http://localhost:8080/h2-console`.

---
Conteúdo preparado para registro da Aula 04: mapeamento com JPA/Hibernate, anotações de entidade e geração de estrutura de banco via JPA.