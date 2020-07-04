package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.domain.Pedido;
import com.raphael.cursoudemy.repositories.PedidoRepository;
import com.raphael.cursoudemy.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso/api/v1/pedidos")
public class PedidoResources {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<Pedido>list(){
        return pedidoRepository.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Pedido pedido = pedidoService.findById(id);

        return ResponseEntity.ok().body(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Integer id, @RequestBody Pedido pedido){

        if (pedidoService.findById(id)==null) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id);
        pedido = pedidoService.insert(pedido);

        return ResponseEntity.ok(pedido);

    }

}
