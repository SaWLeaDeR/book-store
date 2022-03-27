package com.base.converter.impl;

import com.base.converter.GenericConverter;
import com.base.error.model.dto.ExceptionDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

@Component("exceptionDtoConverter")
public class ExceptionDtoConverter implements GenericConverter<Throwable, ExceptionDto> {

    @Override
    public ExceptionDto convert(Throwable source) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ExceptionUtils.getMessage(source));
        exceptionDto.setRootCause(ExceptionUtils.getRootCauseMessage(source));
        exceptionDto.setApplicationLogStackTrace(ExceptionUtils.getStackTrace(source));
        return exceptionDto;
    }
}
