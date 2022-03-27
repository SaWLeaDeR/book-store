package com.base.error.model;

public enum ErrorEnum {

    RESOURCE_NOT_FOUND("resourceNotFound"),
    UNHANDLED_APPLICATION_ERROR("unhandledApplicationError"),
    REQUEST_NOT_VERIFIED("requestNotVerified"),
    MALFORMED_REQUEST("malformedRequest"),
    REQUEST_CANNOT_BE_COMPLETED("requestCannotBeCompleted");

    private String name;

    ErrorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
