package com.pipecode.kardexsales.exception;

import lombok.Getter;

@Getter
public class NotFoundElementException extends RuntimeException{

    private static final long serialVersionUID = 3428878795509908686L;

    private String entityName;

    public NotFoundElementException(String message) {
        super(message);
    }

    public NotFoundElementException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

}
