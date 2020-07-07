package com.raphael.cursoudemy.services;

import com.raphael.cursoudemy.domain.Categoria;
import com.raphael.cursoudemy.domain.Cidade;
import com.raphael.cursoudemy.domain.Cliente;
import com.raphael.cursoudemy.domain.Endereco;
import com.raphael.cursoudemy.domain.enums.TipoCliente;
import com.raphael.cursoudemy.dto.ClienteDTO;
import com.raphael.cursoudemy.dto.ClienteNewDTO;
import com.raphael.cursoudemy.repositories.ClienteRepository;
import com.raphael.cursoudemy.repositories.EnderecoRepository;
import com.raphael.cursoudemy.services.exceptions.DataIntegrityException;
import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());

        return obj;
    }


    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id) {
        try {
            findById(id);
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Exclusão não permitida, cliente possui relacionamento com demais tabelas!");
        }

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO categoriaDTO) {
        return new Cliente(categoriaDTO.getId(), categoriaDTO.getNome(), categoriaDTO.getEmail(), null, null);

    }

    public Cliente fromDTO(ClienteNewDTO categoriaDTO) {
        Cliente cliente = new Cliente(null, categoriaDTO.getNome(), categoriaDTO.getEmail(), categoriaDTO.getCpfCnpj(), TipoCliente.toEnum(categoriaDTO.getTipoCliente()));
        Cidade cidade = new Cidade(categoriaDTO.getCidadeId(), null, null);
        Endereco endereco=new Endereco(null, categoriaDTO.getRua(), categoriaDTO.getNumero(), categoriaDTO.getComplemento(), categoriaDTO.getBairro(), categoriaDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(categoriaDTO.getTelefone1());

        if(categoriaDTO.getTelefone2() != null){
            cliente.getTelefones().add(categoriaDTO.getTelefone2());
        }
        if(categoriaDTO.getTelefone3() != null){
            cliente.getTelefones().add(categoriaDTO.getTelefone3());
        }

        return cliente;


    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());

    }
}
