package com.base.error.factory.impl;

import com.base.error.factory.ServiceResponseFactory;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulServiceResponseFactory implements ServiceResponseFactory {

    @Override
    public <S extends GenericDto, R extends EndpointResponse<S>> ServiceResponse<S> factory(R externalResponse, String... errorNamespaces) {
        return ServiceResponse.of(externalResponse.getData());
    }

    @Override
    public <S extends GenericDto, R extends EndpointResponse<S>> boolean supports(R externalResponse) {
        return externalResponse.getHttpStatus() == HttpStatus.OK && externalResponse.getErrors() == null;
    }
}
