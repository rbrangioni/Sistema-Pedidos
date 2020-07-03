package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.domain.Pedido;
import com.raphael.cursoudemy.repositories.PedidoRepository;
import com.raphael.cursoudemy.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curso/api/v1/pedidos")
public class PedidoResources {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<Pedido>listarPedidos(){
        return pedidoRepository.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<?> BuscaPorId(@PathVariable Integer id){
        Pedido pedido = pedidoService.find(id);

        return ResponseEntity.ok().body(pedido);
    }

}
