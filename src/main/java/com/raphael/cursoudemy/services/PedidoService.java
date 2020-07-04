package com.raphael.cursoudemy.services;

import com.raphael.cursoudemy.domain.Pedido;
import com.raphael.cursoudemy.repositories.PedidoRepository;
import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


    public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido insert(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido update(Pedido pedido){
        findById(pedido.getId());
        return pedidoRepository.save(pedido);
    }
}
