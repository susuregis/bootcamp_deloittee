package com.example.demo;

import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;
import java.util.List;
import java.util.Scanner;

public class TerminalMain {

    private static final CategoriaService categoriaService = new CategoriaService();

    public static void main(String[] args) {
        System.out.println("=== Sistema CRUD - Java Puro com Hibernate ===\n");
        
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> salvarCategoria(scanner);
                case 2 -> listarCategorias();
                case 3 -> buscarCategoriaPorId(scanner);
                case 4 -> deletarCategoria(scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
        System.out.println("Aplicação finalizada.");
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Salvar Categoria");
        System.out.println("2. Listar Categorias");
        System.out.println("3. Buscar Categoria por ID");
        System.out.println("4. Deletar Categoria");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    private static void salvarCategoria(Scanner scanner) {
        System.out.print("Nome da categoria: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Categoria categoria = new Categoria(nome, descricao);
        categoriaService.salvar(categoria);
        System.out.println("✓ Categoria salva com sucesso!");
    }

    private static void listarCategorias() {
        List<Categoria> categorias = categoriaService.listarTodas();
        System.out.println("\n=== Categorias ===");
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
        } else {
            categorias.forEach(c -> 
                System.out.printf("ID: %d | Nome: %s | Descrição: %s%n", 
                    c.getId(), c.getNome(), c.getDescricao())
            );
        }
    }

    private static void buscarCategoriaPorId(Scanner scanner) {
        System.out.print("ID da categoria: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        categoriaService.buscarPorId(id).ifPresentOrElse(
            c -> System.out.printf("Categoria: %s - %s%n", c.getNome(), c.getDescricao()),
            () -> System.out.println("Categoria não encontrada.")
        );
    }

    private static void deletarCategoria(Scanner scanner) {
        System.out.print("ID da categoria para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        categoriaService.deletar(id);
        System.out.println("✓ Categoria deletada!");
    }
}
