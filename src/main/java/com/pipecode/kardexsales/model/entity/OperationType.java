package com.pipecode.kardexsales.model.entity;

public enum OperationType {

    SELL, INVENTORY;

    public static OperationType fromCode(int c) {
        try {
            return OperationType.values()[c];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Tipo de operacion " + c + " invalido", e);
        }
    }
}
