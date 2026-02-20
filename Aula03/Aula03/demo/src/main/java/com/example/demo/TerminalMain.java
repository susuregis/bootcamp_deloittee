package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class TerminalMain implements CommandLineRunner {

    @Autowired
    private PecaService pecaService;

    @Autowired
    private FornecedorService fornecedorService;

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
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        salvarPeca(scanner);
                        break;
                    case 2:
                        listarPecas();
                        break;
                    case 3:
                        buscarPeca(scanner);
                        break;
                    case 4:
                        atualizarPeca(scanner);
                        break;
                    case 5:
                        deletarPeca(scanner);
                        break;
                    case 6:
                        salvarFornecedor(scanner);
                        break;
                    case 7:
                        listarFornecedores();
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
        System.out.println("\n========== SISTEMA DE GERENCIAMENTO ==========");
        System.out.println("=== PEÇAS ===");
        System.out.println("1 - Salvar nova peça");
        System.out.println("2 - Listar todas as peças");
        System.out.println("3 - Buscar peça por ID");
        System.out.println("4 - Atualizar peça");
        System.out.println("5 - Deletar peça");
        System.out.println("=== FORNECEDORES ===");
        System.out.println("6 - Salvar novo fornecedor");
        System.out.println("7 - Listar fornecedores");
        System.out.println("===================");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void salvarPeca(Scanner scanner) {
        System.out.println("\n=== CADASTRAR NOVA PEÇA ===");
        String nome = readLine(scanner, "Nome: ");
        String descricao = readLine(scanner, "Descrição: ");
        String codigo = readLine(scanner, "Código: ");
        BigDecimal preco = readBigDecimal(scanner, "Preço: ");
        Integer estoqueMinimo = readInteger(scanner, "Estoque mínimo: ");

        Long fornecedorId = readLong(scanner, "ID do Fornecedor (ou 0 para sem fornecedor): ");

        Fornecedor fornecedor = null;
        if (fornecedorId > 0) {
            fornecedor = fornecedorService.buscarPorId(fornecedorId).orElse(null);
            if (fornecedor == null) {
                System.out.println("Fornecedor não encontrado. Criando peça sem fornecedor.");
            }
        }

        Peca peca = new Peca(nome, descricao, preco, codigo, estoqueMinimo, fornecedor);
        Peca salva = pecaService.salvar(peca);
        System.out.println("Peça salva com sucesso! ID: " + salva.getId());
    }

    private void listarPecas() {
        System.out.println("\n=== LISTA DE PEÇAS ===");
        List<Peca> pecas = pecaService.listarTodas();
        if (pecas.isEmpty()) {
            System.out.println("Nenhuma peça cadastrada.");
        } else {
            pecas.forEach(p -> {
                String fornecedor = p.getFornecedor() != null ? p.getFornecedor().getNome() : "Sem fornecedor";
                System.out.println("ID: " + p.getId() + " | Código: " + p.getCodigo() + 
                    " | Nome: " + p.getNome() + " | Preço: R$ " + p.getPreco() + 
                    " | Fornecedor: " + fornecedor);
            });
        }
    }

    private void buscarPeca(Scanner scanner) {
        Long id = readLong(scanner, "\nDigite o ID da peça: ");

        try {
            Peca peca = pecaService.buscarPorId(id).orElse(null);
            System.out.println("\n=== PEÇA ENCONTRADA ===");
            System.out.println("ID: " + peca.getId());
            System.out.println("Código: " + peca.getCodigo());
            System.out.println("Nome: " + peca.getNome());
            System.out.println("Descrição: " + peca.getDescricao());
            System.out.println("Preço: R$ " + peca.getPreco());
            System.out.println("Estoque mínimo: " + peca.getEstoqueMinimo());
            if (peca.getFornecedor() != null) {
                System.out.println("Fornecedor: " + peca.getFornecedor().getNome());
            }
        } catch (Exception e) {
            System.out.println("Peça não encontrada!");
        }
    }

    private void atualizarPeca(Scanner scanner) {
        Long id = readLong(scanner, "\nDigite o ID da peça para atualizar: ");

        try {
            Peca peca = pecaService.buscarPorId(id).orElse(null);
            System.out.println("Nome atual: " + peca.getNome());
            System.out.print("Novo nome (ENTER para manter): ");
            String nome = scanner.nextLine();
            if (!nome.isBlank()) {
                peca.setNome(nome);
            }

            System.out.println("Preço atual: R$ " + peca.getPreco());
            BigDecimal preco = readBigDecimal(scanner, "Novo preço (0 para manter): ");
            if (preco.compareTo(BigDecimal.ZERO) > 0) {
                peca.setPreco(preco);
            }

            pecaService.salvar(peca);
            System.out.println("Peça atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar peça: " + e.getMessage());
        }
    }

    private void deletarPeca(Scanner scanner) {
        Long id = readLong(scanner, "\nDigite o ID da peça para deletar: ");

        try {
            pecaService.deletar(id);
            System.out.println("Peça deletada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao deletar peça: " + e.getMessage());
        }
    }

    private void salvarFornecedor(Scanner scanner) {
        System.out.println("\n=== CADASTRAR NOVO FORNECEDOR ===");
        String nome = readLine(scanner, "Nome: ");
        String cnpj = readLine(scanner, "CNPJ: ");
        String email = readLine(scanner, "Email: ");
        String telefone = readLine(scanner, "Telefone: ");

        Fornecedor fornecedor = new Fornecedor(nome, null, email, telefone, cnpj);
        Fornecedor salvo = fornecedorService.salvar(fornecedor);
        System.out.println("Fornecedor salvo com sucesso! ID: " + salvo.getId());
    }

    private void listarFornecedores() {
        System.out.println("\n=== LISTA DE FORNECEDORES ===");
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            fornecedores.forEach(f -> 
                System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() + 
                    " | CNPJ: " + f.getCnpj() + 
                    " | Email: " + (f.getEmail() != null ? f.getEmail() : "N/A"))
            );
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
