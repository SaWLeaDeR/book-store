package com.base.controller.builder.impl;

import com.base.controller.builder.EndpointResponseBuilder;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SuccessfulResponseBuilder<B extends GenericDto, R extends ServiceResponse<B>, C extends EndpointResponse<B>>
        implements EndpointResponseBuilder<B, R, C> {
    @Override
    public void build(R methodResult, C endpointResponse) {
        Optional<B> data = methodResult.getData();
        if (!methodResult.hasErrors() && data.isPresent()) {
            endpointResponse.setData(data.get());
        }
    }
}
