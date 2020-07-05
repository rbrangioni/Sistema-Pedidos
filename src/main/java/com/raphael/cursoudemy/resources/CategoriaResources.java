package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.dto.CategoriaDTO;
import com.raphael.cursoudemy.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso/api/v1/categorias")
public class CategoriaResources {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<Categoria> list = categoriaService.listAll();
        List<CategoriaDTO> dtoList = list.stream().map( obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<?>findById(@PathVariable Integer id){
        Categoria categoria = categoriaService.findById(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {

        Categoria obj = categoriaService.fromDTO(objDto);

        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
        Categoria obj = categoriaService.fromDTO(objDto);
        obj.setId(id);
        obj = categoriaService.update(obj);

        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> detele (@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> listarPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                         @RequestParam(value = "orderBy", defaultValue = "descricao")String orderBy,
                                                         @RequestParam(value = "direction", defaultValue = "ASC")String direction) {

        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);

        Page<CategoriaDTO> dtoList = list.map( obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(dtoList);
    }

}
