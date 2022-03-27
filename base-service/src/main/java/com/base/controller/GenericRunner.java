package com.base.controller;

import com.base.controller.builder.EndpointResponseBuilder;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class GenericRunner<T, D extends GenericDto> implements Runner<T, D> {


    private List<EndpointResponseBuilder> responseBuilders;

    @Autowired
    public void setResponseBuilders(List<EndpointResponseBuilder> responseBuilders) {
        this.responseBuilders = responseBuilders;
    }

    @Override
    public ResponseEntity<EndpointResponse<D>> run(T arg, Function<T, ServiceResponse<D>> function) {
        ServiceResponse<D> methodResult = function.apply(arg);
        EndpointResponse<D> endpointResponse = buildEndpointResponse(methodResult);
        return new ResponseEntity<>(endpointResponse, endpointResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<EndpointResponse> runVoid(T arg, Consumer<T> function) {
        function.accept(arg);
        EndpointResponse endpointResponse = new EndpointResponse();
        return new ResponseEntity<>(endpointResponse, endpointResponse.getHttpStatus());
    }

    private EndpointResponse<D> buildEndpointResponse(ServiceResponse<D> methodResult) {
        EndpointResponse<D> endpointResponse = new EndpointResponse<>();
        responseBuilders.forEach(responseBuilder -> responseBuilder.build(methodResult, endpointResponse));
        return endpointResponse;
    }

}
