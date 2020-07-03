package com.raphael.cursoudemy.resources.exceptionhandler;

import com.raphael.cursoudemy.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StandardError>objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest){
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getLocalizedMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
}
