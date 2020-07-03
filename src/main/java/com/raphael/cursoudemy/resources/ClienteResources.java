package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Cliente;
import com.raphael.cursoudemy.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curso/api/v1/clientes")
public class ClienteResources {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listaCliente(){
        return clienteRepository.findAll();
    }

}
