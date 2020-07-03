package com.raphael.cursoudemy.repositories;

import com.raphael.cursoudemy.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{
}
