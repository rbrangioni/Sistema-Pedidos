package com.raphael.cursoudemy.dto;

import com.raphael.cursoudemy.domain.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo não pode ser nulo")
    @Size(min = 3, max = 60, message = "Quantidade de Caracteres deve estar entre 3 e 60")
    private String nome;

    @NotEmpty(message = "Campo não pode ser nulo")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente){
        id=cliente.getId();
        nome=cliente.getNome();
        email=cliente.getEmail();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
