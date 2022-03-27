package com.base.converter.impl;

import com.base.converter.GenericConverter;
import com.base.error.model.ServiceError;
import com.base.error.model.dto.ErrorDto;
import com.base.error.model.dto.ExceptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("errorDtoConverter")
public class ErrorDtoConverter implements GenericConverter<ServiceError, ErrorDto> {

    private GenericConverter<Throwable, ExceptionDto> exceptionDtoConverter;

    @Autowired
    @Qualifier("exceptionDtoConverter")
    public void setExceptionDtoConverter(GenericConverter<Throwable, ExceptionDto> exceptionDtoConverter) {
        this.exceptionDtoConverter = exceptionDtoConverter;
    }

    @Override
    public ErrorDto convert(ServiceError serviceError) {
        ErrorDto error = new ErrorDto();
        error.setErrorEnum(serviceError.getErrorType());
        error.setErrorDetail(serviceError.getServiceErrorDetail());
        error.setErrorLogLevel(serviceError.getErrorLogLevel());
        error.setCreateDate(serviceError.getCreateDate());
        if (null != serviceError.getException()) {
            error.setException(exceptionDtoConverter.convert(serviceError.getException()));
        }
        return error;
    }

}
