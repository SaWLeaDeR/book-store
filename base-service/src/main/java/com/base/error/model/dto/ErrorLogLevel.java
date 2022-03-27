package com.base.error.model.dto;

public enum ErrorLogLevel {

    INFO("info"),
    WARNING("warning"),
    ERROR("error");

    private String name;

    ErrorLogLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
