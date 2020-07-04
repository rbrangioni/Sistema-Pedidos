package com.raphael.cursoudemy.resources.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessages>  erros = new ArrayList<>();


    public ValidationError(Integer status, String msg, LocalDateTime timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessages> getErrors() {
        return erros;
    }

    public void addError(String nomeCampo, String mensagem){
        erros.add(new FieldMessages(nomeCampo, mensagem));
    }
}
