package com.base.bookservice.validation.model.dto;

public class BookValidatorInput<T> {

    private T request;

    public BookValidatorInput(T request) {
        this.request = request;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
