package com.pipecode.kardexsales.validator;


import java.util.function.Consumer;

@FunctionalInterface
public interface BaseValidator extends Consumer<Object> {

    @Override
    void accept(final Object object);
}
