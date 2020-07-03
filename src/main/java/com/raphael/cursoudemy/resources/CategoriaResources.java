package com.raphael.cursoudemy.resources;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.repositories.CategoriaRepository;
import com.raphael.cursoudemy.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso/api/v1/categorias")
public class CategoriaResources {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @GetMapping("{id}")
    public ResponseEntity<?>BuscaPorId(@PathVariable Integer id){
        Categoria categoria = categoriaService.find(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar(@RequestBody Categoria categoria){
        return categoriaService.salvar(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Integer id, @RequestBody Categoria categoria){

        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        categoria = categoriaService.salvar(categoria);

        return ResponseEntity.ok(categoria);

    }
}
