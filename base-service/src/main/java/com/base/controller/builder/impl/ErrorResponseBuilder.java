package com.base.controller.builder.impl;

import com.base.controller.builder.EndpointResponseBuilder;
import com.base.converter.GenericConverter;
import com.base.error.model.ErrorSignDto;
import com.base.error.model.ServiceError;
import com.base.error.model.dto.ErrorDto;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ErrorResponseBuilder<B extends GenericDto, R extends ServiceResponse<B>, C extends EndpointResponse<B>>
        implements EndpointResponseBuilder<B, R, C> {

    @Autowired
    @Qualifier("errorDtoConverter")
    private GenericConverter<ServiceError, ErrorDto> errorDtoConverter;

    @Override
    public void build(R methodResult, C endpointResponse) {
        convertAndWrite(methodResult, endpointResponse);
    }

    private void convertAndWrite(R serviceResponse, C endpointResponse) {
        if (serviceResponse.hasErrors()) {
            ErrorSignDto errorSignDto = new ErrorSignDto();
            errorSignDto.addErrors(
                    serviceResponse.getErrors().stream().map(errorDtoConverter::convert).collect(Collectors.toSet()));
            endpointResponse.setErrors(errorSignDto);
        }
    }

}
