package com.raphael.cursoudemy.repositories;

import com.raphael.cursoudemy.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
