package com.base.controller;

import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;
import java.util.function.Function;


public abstract class GenericController<T> {

    private Runner runner;
    private T serviceProvider;

    protected <R extends GenericDto> ResponseEntity<EndpointResponse<R>> run(
            Function<T, ServiceResponse<R>> function) {
        return runner.run(serviceProvider, function);
    }

    protected ResponseEntity<EndpointResponse> runVoid(Consumer<T> function) {
        return runner.runVoid(serviceProvider, function);
    }

    protected Runner getRunner() {
        return runner;
    }

    public final void setRunner(Runner runner) {
        this.runner = runner;
    }

    public final void setServiceProvider(T serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

}
