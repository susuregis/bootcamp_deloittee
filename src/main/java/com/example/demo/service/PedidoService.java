package com.example.demo.service;

import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações de Pedido.
 * Implementa GenericService promovendo DIP (Dependency Inversion Principle)
 * Utiliza constructor injection melhorando testabilidade
 * Separa lógica de negócio da apresentação (SRP - Single Responsibility Principle)
 */
@Service
public class PedidoService implements GenericService<Pedido, Long> {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listarTodas() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return pedidoRepository.existsById(id);
    }

    public Optional<Pedido> buscarPorNumero(String numero) {
        return pedidoRepository.findByNumero(numero);
    }
}
