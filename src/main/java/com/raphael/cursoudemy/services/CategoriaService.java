package com.raphael.cursoudemy.services;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.domain.Cliente;
import com.raphael.cursoudemy.dto.CategoriaDTO;
import com.raphael.cursoudemy.repositories.CategoriaRepository;
import com.raphael.cursoudemy.services.exceptions.DataIntegrityException;
import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }


    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = findById(obj.getId());
        updateData(newObj, obj);
        return categoriaRepository.save(newObj);
    }

    public void delete(Integer id) {
        try {
            findById(id);
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Exclusão não permitida, categoria possui produtos vinculados!");
        }

    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
       return new Categoria(categoriaDTO.getId(), categoriaDTO.getDescricao());
    }

    private void updateData(Categoria newObj, Categoria obj){
        newObj.setDescricao(obj.getDescricao());

    }

}
