package com.base.customer.validator.model.dto;

import com.base.model.request.GenericRequest;

public class CustomerValidatorInput<T extends GenericRequest> {

    private T request;

    public CustomerValidatorInput(T request) {
        this.request = request;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
