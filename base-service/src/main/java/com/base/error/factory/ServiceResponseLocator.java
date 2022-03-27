package com.base.error.factory;

import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceResponseLocator {

    private final List<ServiceResponseFactory> serviceResponseFactories;

    @Autowired
    public ServiceResponseLocator(List<ServiceResponseFactory> serviceResponseFactories) {
        this.serviceResponseFactories = serviceResponseFactories;
    }

    public <S extends GenericDto, R extends EndpointResponse<S>> ServiceResponse<S> factory(R externalResponse,
                                                                                            String... errorNamespaces) {

        return serviceResponseFactories
            .stream()
            .filter(f -> f.supports(externalResponse))
            .findFirst()
            .orElseThrow(() -> new UnhandledResponseException("Service response cannot be properly handled"))
            .factory(externalResponse, errorNamespaces);

    }
}
