package com.raphael.cursoudemy.services;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.repositories.CategoriaRepository;
import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }


    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
