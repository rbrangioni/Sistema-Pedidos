package com.raphael.cursoudemy.repositories;

import com.raphael.cursoudemy.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer>{
}
