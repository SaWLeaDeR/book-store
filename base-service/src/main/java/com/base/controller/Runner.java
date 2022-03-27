package com.base.controller;

import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Runner<T, D extends GenericDto> {

    ResponseEntity<EndpointResponse<D>> run(T arg, Function<T, ServiceResponse<D>> function);

    ResponseEntity<EndpointResponse> runVoid(T arg, Consumer<T> consumer);
}
