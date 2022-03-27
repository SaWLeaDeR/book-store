package com.base.controller.builder;

import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;

public interface EndpointResponseBuilder<B extends GenericDto, R extends ServiceResponse<B>, C extends EndpointResponse<B>> {

    void build(R methodResult, C endpointResponse);

}
