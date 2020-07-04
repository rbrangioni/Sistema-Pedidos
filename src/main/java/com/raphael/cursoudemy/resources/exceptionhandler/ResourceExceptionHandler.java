package com.raphael.cursoudemy.resources.exceptionhandler;

import com.raphael.cursoudemy.services.exceptions.DataIntegrityException;
import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError>objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest){
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getLocalizedMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError>dataIntegrity(DataIntegrityException dataIntegrityException, HttpServletRequest httpServletRequest){
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), dataIntegrityException.getLocalizedMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError>validation(MethodArgumentNotValidException dataIntegrityException, HttpServletRequest httpServletRequest){

        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", LocalDateTime.now());

        for(FieldError x : dataIntegrityException.getBindingResult().getFieldErrors()){
            validationError.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
