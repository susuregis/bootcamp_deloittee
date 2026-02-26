package com.example.demo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.model.Fornecedor;
import com.example.demo.model.Peca;
import com.example.demo.service.FornecedorService;
import com.example.demo.service.PecaService;

// @Component - Desativado para não executar automaticamente na API
public class TerminalMain implements CommandLineRunner {

    @Autowired
    private PecaService pecaService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ApplicationContext context;

    // Método main removido - use ProjectCrudApplication.main() para iniciar a API
    
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
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

        scanner.close();
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
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Preço: ");
        BigDecimal preco = scanner.nextBigDecimal();
        System.out.print("Estoque mínimo: ");
        Integer estoqueMinimo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do Fornecedor (ou 0 para sem fornecedor): ");
        Long fornecedorId = scanner.nextLong();
        scanner.nextLine();

        Fornecedor fornecedor = null;
        if (fornecedorId > 0) {
            try {
                fornecedor = fornecedorService.buscarPorId(fornecedorId).orElse(null);
                if (fornecedor == null) {
                    System.out.println("Fornecedor não encontrado. Criando peça sem fornecedor.");
                }
            } catch (Exception e) {
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
        System.out.print("\nDigite o ID da peça: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        
        try {
            Peca peca = pecaService.buscarPorId(id).orElseThrow(() -> new Exception("Peça não encontrada"));
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
            System.out.println("✗ Peça não encontrada!");
        }
    }

    private void atualizarPeca(Scanner scanner) {
        System.out.print("\nDigite o ID da peça para atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            Peca peca = pecaService.buscarPorId(id).orElseThrow(() -> new Exception("Peça não encontrada"));
            System.out.println("Nome atual: " + peca.getNome());
            System.out.print("Novo nome (ENTER para manter): ");
            String nome = scanner.nextLine();
            if (!nome.isBlank()) {
                peca.setNome(nome);
            }

            System.out.println("Preço atual: R$ " + peca.getPreco());
            System.out.print("Novo preço (0 para manter): ");
            BigDecimal preco = scanner.nextBigDecimal();
            scanner.nextLine();
            if (preco.compareTo(BigDecimal.ZERO) > 0) {
                peca.setPreco(preco);
            }

            pecaService.salvar(peca);
            System.out.println("✓ Peça atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("✗ Erro ao atualizar peça: " + e.getMessage());
        }
    }

    private void deletarPeca(Scanner scanner) {
        System.out.print("\nDigite o ID da peça para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            pecaService.deletar(id);
            System.out.println("✓ Peça deletada com sucesso!");
        } catch (Exception e) {
            System.out.println("✗ Erro ao deletar peça: " + e.getMessage());
        }
    }

    private void salvarFornecedor(Scanner scanner) {
        System.out.println("\n=== CADASTRAR NOVO FORNECEDOR ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor(nome, cpf, email, telefone, cnpj);
        Fornecedor salvo = fornecedorService.salvar(fornecedor);
        System.out.println("✓ Fornecedor salvo com sucesso! ID: " + salvo.getId());
    }

    private void listarFornecedores() {
        System.out.println("\n=== LISTA DE FORNECEDORES ===");
        List<Fornecedor> fornecedores = fornecedorService.listarTodas();
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
}
