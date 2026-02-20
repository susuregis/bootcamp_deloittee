package com.example.demo;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class TerminalMain implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) {
        if (System.console() == null) {
            System.out.println("Console não detectado; pulando menu interativo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        try {
            do {
                exibirMenu();
                if (!scanner.hasNextInt()) {
                    System.out.println("\nEntrada finalizada. Encerrando aplicação...");
                    SpringApplication.exit(context, () -> 0);
                    break;
                }
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        salvarUsuario(scanner);
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        buscarUsuario(scanner);
                        break;
                    case 4:
                        atualizarUsuario(scanner);
                        break;
                    case 5:
                        deletarUsuario(scanner);
                        break;
                    case 0:
                        System.out.println("Encerrando aplicação...");
                        SpringApplication.exit(context, () -> 0);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 0);
        } catch (java.util.NoSuchElementException e) {
            System.out.println("\nEntrada encerrada (EOF). Encerrando aplicação...");
            SpringApplication.exit(context, () -> 0);
        } finally {
            try { scanner.close(); } catch (IllegalStateException ignored) {}
        }
    }

    private void exibirMenu() {
        System.out.println("\n========== MENU USUÁRIOS ==========");
        System.out.println("1 - Salvar novo usuário");
        System.out.println("2 - Listar todos os usuários");
        System.out.println("3 - Buscar usuário por ID");
        System.out.println("4 - Atualizar usuário");
        System.out.println("5 - Deletar usuário");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void salvarUsuario(Scanner scanner) {
        System.out.println("\n=== CADASTRAR NOVO USUÁRIO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, null);
        Usuario salvo = usuarioService.criarUsuario(usuario);
        System.out.println("Usuário salvo com sucesso! ID: " + salvo.getId());
    }

    private void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        List<Usuario> usuarios = usuarioService.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(u -> 
                System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail())
            );
        }
    }

    private void buscarUsuario(Scanner scanner) {
        Long id = readLong(scanner, "\nDigite o ID do usuário: ");

        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            System.out.println("\n=== USUÁRIO ENCONTRADO ===");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
        } catch (Exception e) {
            System.out.println("Usuário não encontrado!");
        }
    }

    private void atualizarUsuario(Scanner scanner) {
        Long id = readLong(scanner, "\nDigite o ID do usuário para atualizar: ");

        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            System.out.println("Nome atual: " + usuario.getNome());
            System.out.print("Novo nome (ENTER para manter): ");
            String nome = scanner.nextLine();
            if (!nome.isBlank()) {
                usuario.setNome(nome);
            }

            System.out.println("Email atual: " + usuario.getEmail());
            System.out.print("Novo email (ENTER para manter): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) {
                usuario.setEmail(email);
            }

            usuarioService.atualizarUsuario(id, usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void deletarUsuario(Scanner scanner) {
        Long id = readLong(scanner, "\nDigite o ID do usuário para deletar: ");

        try {
            usuarioService.removerUsuario(id);
            System.out.println("Usuário deletado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    // ========= Helper methods for safe input =========
    private String readLine(Scanner scanner, String prompt) {
        if (prompt != null && !prompt.isEmpty()) System.out.print(prompt);
        if (!scanner.hasNextLine()) throw new java.util.NoSuchElementException("EOF");
        return scanner.nextLine();
    }

    private int readInt(Scanner scanner, String prompt) {
        if (prompt != null && !prompt.isEmpty()) System.out.print(prompt);
        if (!scanner.hasNextInt()) throw new java.util.NoSuchElementException("EOF");
        int v = scanner.nextInt();
        scanner.nextLine();
        return v;
    }

    private Long readLong(Scanner scanner, String prompt) {
        if (prompt != null && !prompt.isEmpty()) System.out.print(prompt);
        if (!scanner.hasNextLong()) throw new java.util.NoSuchElementException("EOF");
        long v = scanner.nextLong();
        scanner.nextLine();
        return Long.valueOf(v);
    }

    private Integer readInteger(Scanner scanner, String prompt) {
        return Integer.valueOf(readInt(scanner, prompt));
    }

    private java.math.BigDecimal readBigDecimal(Scanner scanner, String prompt) {
        if (prompt != null && !prompt.isEmpty()) System.out.print(prompt);
        if (!scanner.hasNextBigDecimal()) throw new java.util.NoSuchElementException("EOF");
        java.math.BigDecimal v = scanner.nextBigDecimal();
        scanner.nextLine();
        return v;
    }
}
