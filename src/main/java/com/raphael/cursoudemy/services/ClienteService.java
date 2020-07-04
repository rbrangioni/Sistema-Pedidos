package com.raphael.cursoudemy.services;

import com.raphael.cursoudemy.domain.Cliente;
import com.raphael.cursoudemy.repositories.ClienteRepository;
import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


    public Cliente insert(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente){
        findById(cliente.getId());
        return clienteRepository.save(cliente);
    }
}
