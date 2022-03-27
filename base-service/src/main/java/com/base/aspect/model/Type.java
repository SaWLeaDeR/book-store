package com.base.aspect.model;

public enum Type {
    BEFORE("BEFORE "),
    AFTER("AFTER ");

    private String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
