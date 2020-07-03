package com.raphael.cursoudemy.services;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.domain.Pedido;
import com.raphael.cursoudemy.repositories.CategoriaRepository;
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

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


    public Pedido salvar(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
