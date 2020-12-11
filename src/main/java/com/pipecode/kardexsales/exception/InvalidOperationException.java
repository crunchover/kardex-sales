package com.pipecode.kardexsales.exception;

import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException{

    private static final long serialVersionUID = 3424578795509908686L;

    private String entityName;

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

}
