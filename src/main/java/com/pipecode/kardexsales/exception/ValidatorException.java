package com.pipecode.kardexsales.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidatorException extends RuntimeException{

    private List<String> violationMessages;

    public ValidatorException(String message, List<String> violationMessages) {
        super(message);
        this.violationMessages = violationMessages;
    }

}
