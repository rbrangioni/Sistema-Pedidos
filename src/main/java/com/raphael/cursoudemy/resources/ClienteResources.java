package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.domain.Cliente;
import com.raphael.cursoudemy.dto.CategoriaDTO;
import com.raphael.cursoudemy.dto.ClienteDTO;
import com.raphael.cursoudemy.dto.ClienteNewDTO;
import com.raphael.cursoudemy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso/api/v1/clientes")
public class ClienteResources {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<Cliente> list = clienteService.listAll();
        List<ClienteDTO> dtoList = list.stream().map( obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<?>findById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {

        Cliente obj = clienteService.fromDTO(objDto);

        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
        Cliente obj = clienteService.fromDTO(objDto);
        obj.setId(id);
        obj = clienteService.update(obj);

        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> detele (@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> listarPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                         @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
                                                         @RequestParam(value = "direction", defaultValue = "ASC")String direction) {

        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);

        Page<ClienteDTO> dtoList = list.map( obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(dtoList);
    }
}
