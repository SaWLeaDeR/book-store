package com.base.error.factory.impl;

import com.base.error.ErrorFactory;
import com.base.error.factory.ServiceResponseFactory;
import com.base.error.model.ErrorEnum;
import com.base.error.model.dto.ErrorLogLevel;
import com.base.error.model.token.UnhandledApplicationError;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UndhandledServiceResponseFactoryImpl implements ServiceResponseFactory {

    @Override
    public <S extends GenericDto, R extends EndpointResponse<S>> ServiceResponse<S> factory(
        R externalResponse, String... errorNamespaces) {
        return ServiceResponse.ofError(ErrorFactory
            .fromErrorToken(new UnhandledApplicationError(errorNamespaces))
            .withErrorMarker(ErrorEnum.MALFORMED_REQUEST)
            .atErrorLogLevel(ErrorLogLevel.ERROR));
    }

    @Override
    public <S extends GenericDto, R extends EndpointResponse<S>> boolean supports(R externalResponse) {
        return true;
    }
}
