package com.example.demo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.model.Categoria;
import com.example.demo.model.Estoque;
import com.example.demo.model.Fornecedor;
import com.example.demo.model.Funcionario;
import com.example.demo.model.Movimentacao;
import com.example.demo.model.Peca;
import com.example.demo.model.Pedido;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EstoqueService;
import com.example.demo.service.FornecedorService;
import com.example.demo.service.FuncionarioService;
import com.example.demo.service.MovimentacaoService;
import com.example.demo.service.PecaService;
import com.example.demo.service.PedidoService;

@Component
public class TerminalMain implements CommandLineRunner {

    @Autowired
    private PecaService pecaService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) {
        // If there's no console available, assume non-interactive run and skip the menu.
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
                        salvarCategoria(scanner);
                        break;
                    case 2:
                        listarCategorias();
                        break;
                    case 3:
                        salvarFornecedor(scanner);
                        break;
                    case 4:
                        listarFornecedores();
                        break;
                    case 5:
                        salvarFuncionario(scanner);
                        break;
                    case 6:
                        listarFuncionarios();
                        break;
                    case 7:
                        salvarPeca(scanner);
                        break;
                    case 8:
                        listarPecas();
                        break;
                    case 9:
                        buscarPeca(scanner);
                        break;
                    case 10:
                        salvarEstoque(scanner);
                        break;
                    case 11:
                        listarEstoques();
                        break;
                    case 12:
                        criarPedido(scanner);
                        break;
                    case 13:
                        listarPedidos();
                        break;
                    case 14:
                        registrarMovimentacao(scanner);
                        break;
                    case 15:
                        listarMovimentacoes();
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
            try {
                scanner.close();
            } catch (IllegalStateException ignored) {
            }
        }
    }

    // ============ CATEGORIA ============
    private void salvarCategoria(Scanner scanner) {
        System.out.println("\n=== CADASTRAR CATEGORIA ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Categoria categoria = new Categoria(nome, descricao);
        Categoria salva = categoriaService.salvar(categoria);
        System.out.println("Categoria salva com sucesso! ID: " + salva.getId());
    }

    private void listarCategorias() {
        System.out.println("\n=== LISTA DE CATEGORIAS ===");
        List<Categoria> categorias = categoriaService.listarTodas();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            categorias.forEach(c -> 
                System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + 
                    " | Descrição: " + c.getDescricao() + 
                    " | Ativa: " + (c.getAtiva() ? "Sim" : "Não"))
            );
        }
    }

    // ============ FORNECEDOR ============
    private void salvarFornecedor(Scanner scanner) {
        System.out.println("\n=== CADASTRAR FORNECEDOR ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor(nome, cpf, email, telefone, cnpj);
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
                    " | CNPJ: " + f.getCnpj() + " | Email: " + f.getEmail())
            );
        }
    }

    // ============ FUNCIONÁRIO ============
    private void salvarFuncionario(Scanner scanner) {
        System.out.println("\n=== CADASTRAR FUNCIONÁRIO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone);
        Funcionario salvo = funcionarioService.salvar(funcionario);
        System.out.println("Funcionário salvo com sucesso! ID: " + salvo.getId());
    }

    private void listarFuncionarios() {
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        List<Funcionario> funcionarios = funcionarioService.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            funcionarios.forEach(f -> 
                System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() + 
                    " | CPF: " + f.getCpf() + " | Email: " + f.getEmail())
            );
        }
    }

    // ============ PEÇA ============
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

        Peca peca = pecaService.buscarPorId(id).orElse(null);
        if (peca != null) {
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
        } else {
            System.out.println("Peça não encontrada!");
        }
    }

    // ============ ESTOQUE ============
    private void salvarEstoque(Scanner scanner) {
        System.out.println("\n=== CADASTRAR ESTOQUE ===");
        Long pecaId = readLong(scanner, "ID da Peça: ");

        Peca peca = pecaService.buscarPorId(pecaId).orElse(null);
        if (peca == null) {
            System.out.println("Peça não encontrada!");
            return;
        }

        Integer quantidade = readInteger(scanner, "Quantidade inicial: ");

        Estoque estoque = new Estoque(peca, quantidade);
        Estoque salvo = estoqueService.salvar(estoque);
        System.out.println("Estoque cadastrado com sucesso! ID: " + salvo.getId());
    }

    private void listarEstoques() {
        System.out.println("\n=== LISTA DE ESTOQUES ===");
        List<Estoque> estoques = estoqueService.listarTodos();
        if (estoques.isEmpty()) {
            System.out.println("Nenhum estoque cadastrado.");
        } else {
            estoques.forEach(e -> 
                System.out.println("ID: " + e.getId() + 
                    " | Peça: " + e.getPeca().getNome() + 
                    " | Quantidade: " + e.getQuantidade() +
                    " | Disponível: " + e.getQuantidadeDisponivel())
            );
        }
    }

    // ============ PEDIDO ============
    private void criarPedido(Scanner scanner) {
        System.out.println("\n=== CRIAR PEDIDO ===");
        Long fornecedorId = readLong(scanner, "ID do Fornecedor: ");

        Fornecedor fornecedor = fornecedorService.buscarPorId(fornecedorId).orElse(null);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado!");
            return;
        }

        Long funcionarioId = readLong(scanner, "ID do Funcionário responsável: ");

        Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId).orElse(null);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        Pedido pedido = new Pedido(fornecedor, funcionario);
        
        String obs = readLine(scanner, "Observações (ou ENTER para pular): ");
        if (!obs.isBlank()) {
            pedido.setObservacoes(obs);
        }

        Pedido salvo = pedidoService.salvar(pedido);
        System.out.println("Pedido criado com sucesso!");
        System.out.println("  Número: " + salvo.getNumero());
        System.out.println("  ID: " + salvo.getId());
    }

    private void listarPedidos() {
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        List<Pedido> pedidos = pedidoService.listarTodos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
        } else {
            pedidos.forEach(p -> 
                System.out.println("ID: " + p.getId() + 
                    " | Número: " + p.getNumero() + 
                    " | Fornecedor: " + p.getFornecedor().getNome() +
                    " | Status: " + p.getStatus())
            );
        }
    }

    // ============ MOVIMENTAÇÃO ============
    private void registrarMovimentacao(Scanner scanner) {
        System.out.println("\n=== REGISTRAR MOVIMENTAÇÃO ===");
        Long pecaId = readLong(scanner, "ID da Peça: ");

        Peca peca = pecaService.buscarPorId(pecaId).orElse(null);
        if (peca == null) {
            System.out.println("Peça não encontrada!");
            return;
        }

        Long funcionarioId = readLong(scanner, "ID do Funcionário: ");

        Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId).orElse(null);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        System.out.println("Tipo de movimentação:");
        System.out.println("1 - ENTRADA");
        System.out.println("2 - SAIDA");
        System.out.println("3 - AJUSTE");
        int tipoEscolha = readInt(scanner, "Escolha: ");

        Movimentacao.TipoMovimentacao tipo;
        switch (tipoEscolha) {
            case 1: tipo = Movimentacao.TipoMovimentacao.ENTRADA; break;
            case 2: tipo = Movimentacao.TipoMovimentacao.SAIDA; break;
            case 3: tipo = Movimentacao.TipoMovimentacao.AJUSTE; break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }

        Integer quantidade = readInteger(scanner, "Quantidade: ");

        String obs = readLine(scanner, "Observação (ou ENTER para pular): ");

        Movimentacao movimentacao = new Movimentacao(peca, funcionario, tipo, quantidade, obs.isBlank() ? null : obs);

        Movimentacao salva = movimentacaoService.salvar(movimentacao);
        System.out.println("Movimentação registrada com sucesso! ID: " + salva.getId());
    }

    private void listarMovimentacoes() {
        System.out.println("\n=== LISTA DE MOVIMENTAÇÕES ===");
        List<Movimentacao> movimentacoes = movimentacaoService.listarTodas();
        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            movimentacoes.forEach(m -> 
                System.out.println("ID: " + m.getId() + 
                    " | Peça: " + m.getPeca().getNome() + 
                    " | Tipo: " + m.getTipo() +
                    " | Quantidade: " + m.getQuantidade() +
                    " | Data: " + m.getDataMovimentacao())
            );
        }
    }

    // ============ MÉTODOS DE MENU ============
    private void exibirMenu() {
        System.out.println("\n========== SISTEMA DE GERENCIAMENTO ==========");
        System.out.println("=== CATEGORIAS ===");
        System.out.println("1 - Cadastrar categoria");
        System.out.println("2 - Listar categorias");
        System.out.println("=== FORNECEDORES ===");
        System.out.println("3 - Cadastrar fornecedor");
        System.out.println("4 - Listar fornecedores");
        System.out.println("=== FUNCIONÁRIOS ===");
        System.out.println("5 - Cadastrar funcionário");
        System.out.println("6 - Listar funcionários");
        System.out.println("=== PEÇAS ===");
        System.out.println("7 - Cadastrar peça");
        System.out.println("8 - Listar peças");
        System.out.println("9 - Buscar peça por ID");
        System.out.println("=== ESTOQUE ===");
        System.out.println("10 - Cadastrar estoque");
        System.out.println("11 - Listar estoques");
        System.out.println("=== PEDIDOS ===");
        System.out.println("12 - Criar pedido");
        System.out.println("13 - Listar pedidos");
        System.out.println("=== MOVIMENTAÇÕES ===");
        System.out.println("14 - Registrar movimentação");
        System.out.println("15 - Listar movimentações");
        System.out.println("===================");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
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