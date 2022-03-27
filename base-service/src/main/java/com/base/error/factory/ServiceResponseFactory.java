package com.base.error.factory;

import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;

public interface ServiceResponseFactory {

    <S extends GenericDto, R extends EndpointResponse<S>> ServiceResponse<S> factory(R externalResponse,
                                                                                     String... errorNamespaces);

    <S extends GenericDto, R extends EndpointResponse<S>> boolean supports(R externalResponse);
}
