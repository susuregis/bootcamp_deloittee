# Aula 03 — Programação Orientada a Objetos (POO)

## Visão geral
Nesta aula foram abordados os conceitos fundamentais de Programação Orientada a Objetos (POO) em Java: classes, objetos, atributos, métodos, encapsulamento, herança, polimorfismo e abstração. O foco foi compreender como modelar domínio usando tipos, organizar comportamento em métodos e reutilizar código com hierarquias.

## Objetivos de aprendizagem
- Entender o que são classes e objetos e como representam entidades do domínio.
- Aplicar encapsulamento para proteger estado interno de uma classe.
- Utilizar herança para reutilização e especialização de comportamento.
- Demonstrar polimorfismo para escrever código genérico e extensível.
- Projetar classes coesas que respeitem responsabilidades claras.

## Conceitos principais
- Classe: definição do tipo, contém atributos (estado) e métodos (comportamento).
- Objeto: instância de uma classe com valores concretos.
- Atributos: variáveis que representam estado de um objeto.
- Métodos: funções que definem comportamento da classe.
- Encapsulamento: controle de acesso a atributos e métodos via modificadores (`private`, `protected`, `public`) e uso de getters/setters.
- Abstração: expor apenas o necessário para usar um objeto, escondendo detalhes de implementação.

### Herança
- Permite definir uma classe base (superclasse) e classes especializadas (subclasses).
- Uso de `extends` em Java para criar relações "é-um".
- Reutiliza código e permite sobrescrever métodos usando `@Override`.

### Polimorfismo
- Permite tratar objetos distintos de forma uniforme por meio do tipo da superclasse ou interfaces.
- Em tempo de execução, a implementação correta (sobrescrita) é chamada.
- Facilita a extensibilidade e reduz acoplamento.

## Exemplos (em Java)
Exemplo simples de classe e herança:

```java
// Classe base
public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void apresentar() {
        System.out.println("Olá, eu sou " + nome);
    }
}

// Subclasse que herda de Pessoa
public class Aluno extends Pessoa {
    private String matricula;

    public Aluno(String nome, String matricula) {
        super(nome);
        this.matricula = matricula;
    }

    @Override
    public void apresentar() {
        System.out.println("Aluno: " + getNome() + ", matrícula: " + matricula);
    }
}
```


---
Conteúdo preparado para registro da Aula 03: conceitos e práticas de POO — classes, herança e polimorfismo.