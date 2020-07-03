package com.raphael.cursoudemy.repositories;

import com.raphael.cursoudemy.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
