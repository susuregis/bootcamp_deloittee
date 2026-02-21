package com.example.demo.repository;

import com.example.demo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumero(String numero);
    List<Pedido> findByFornecedorId(Long fornecedorId);
    List<Pedido> findByStatus(Pedido.StatusPedido status);
}
