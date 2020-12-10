package com.pipecode.kardexsales;


import com.pipecode.kardexsales.exception.NotFoundElementException;
import com.pipecode.kardexsales.exception.ValidatorException;
import com.pipecode.kardexsales.model.web.SimpleErrorMessage;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class MainExceptionHandler {

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<SimpleErrorMessage> handleValidatorException(ValidatorException ex) {
        return new ResponseEntity<SimpleErrorMessage>(new SimpleErrorMessage(
                ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toString(),
                "invalid-data",
                "Informacion invalida",
                ex.getMessage(),
                String.join(System.lineSeparator(), ex.getViolationMessages())), UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundElementException.class)
    public ResponseEntity<SimpleErrorMessage> handleItemNotFoundException(NotFoundElementException ex) {
        return new ResponseEntity<SimpleErrorMessage>(new SimpleErrorMessage(
                ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toString(),
                "item-not-found",
                "Entidad requerida o solicitada no encontrada",
                ex.getEntityName(),
                ex.getMessage()), NOT_FOUND);
    }

}