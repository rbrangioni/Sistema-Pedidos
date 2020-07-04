package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Cliente;
import com.raphael.cursoudemy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso/api/v1/clientes")
public class ClienteResources {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> list(){
        return clienteService.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente insert(@RequestBody Cliente cliente){
        return clienteService.insert(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente){

        if (clienteService.findById(id)==null) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = clienteService.insert(cliente);

        return ResponseEntity.ok(cliente);

    }
}
