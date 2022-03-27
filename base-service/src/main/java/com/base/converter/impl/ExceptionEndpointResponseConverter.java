package com.base.converter.impl;

import com.base.converter.GenericConverter;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ErrorSignDto;
import com.base.error.model.dto.ErrorDto;
import com.base.error.model.dto.ErrorLogLevel;
import com.base.error.model.dto.ExceptionDto;
import com.base.error.model.token.UnhandledApplicationError;
import com.base.model.response.EndpointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("exceptionEndpointResponseConverter")
public class ExceptionEndpointResponseConverter implements
                                                GenericConverter<Throwable, EndpointResponse> {

    private final GenericConverter<Throwable, ExceptionDto> exceptionDtoConverter;

    @Autowired
    public ExceptionEndpointResponseConverter(
            @Qualifier("exceptionDtoConverter") GenericConverter<Throwable, ExceptionDto> exceptionDtoConverter) {
        this.exceptionDtoConverter = exceptionDtoConverter;
    }

    @Override
    public EndpointResponse convert(Throwable source) {
        final ErrorDto serviceError = getExceptionResponseDto(source);
        final ErrorSignDto errorSigns = new ErrorSignDto(serviceError);

        EndpointResponse endpointResponse = new EndpointResponse();
        endpointResponse.setErrors(errorSigns);
        endpointResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return endpointResponse;
    }

    private ErrorDto getExceptionResponseDto(Throwable exception) {
        ErrorDto error = new ErrorDto();
        error.setErrorDetail(new UnhandledApplicationError());
        error.setErrorLogLevel(ErrorLogLevel.ERROR);
        error.setErrorEnum(ErrorEnum.UNHANDLED_APPLICATION_ERROR);
        error.setException(exceptionDtoConverter.convert(exception));
        return error;
    }

}
