package com.base.error.factory.impl;

import com.base.converter.ServiceErrorConverter;
import com.base.error.factory.ServiceResponseFactory;
import com.base.error.model.ServiceError;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Component
public class ErrorSignServiceResponseFactory implements ServiceResponseFactory {

    @Autowired
    private ServiceErrorConverter serviceErrorConverter;

    @Override
    public <S extends GenericDto, R extends EndpointResponse<S>> ServiceResponse<S> factory(
        R externalResponse, String... errorNamespaces) {
        ServiceResponse<S> optionalMethodResult = ServiceResponse.empty();
        Collection<ServiceError> serviceErrors = serviceErrorConverter.convert(externalResponse.getErrors().getErrors(), errorNamespaces);
        optionalMethodResult.getServiceErrors().addAll(serviceErrors);
        return optionalMethodResult;
    }

    @Override
    public <S extends GenericDto, R extends EndpointResponse<S>> boolean supports(R externalResponse) {
        return externalResponse.getErrors() != null
            && !CollectionUtils.isEmpty(externalResponse.getErrors().getErrors());
    }
}
