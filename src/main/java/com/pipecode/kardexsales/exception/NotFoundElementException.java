package com.pipecode.kardexsales.exception;

public class NotFoundElementException extends RuntimeException{

    private static final long serialVersionUID = 3428878795509908686L;

    public NotFoundElementException(String message) {
        super(message);
    }
}
