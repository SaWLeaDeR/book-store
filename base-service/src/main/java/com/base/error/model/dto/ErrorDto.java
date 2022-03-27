package com.base.error.model.dto;

import com.base.error.model.ServiceErrorDetail;
import com.base.error.model.ErrorEnum;
import com.base.error.model.GenericErrorDto;

public class ErrorDto extends GenericErrorDto {
    private static final long serialVersionUID = -5324106148394277538L;

    private ErrorEnum errorEnum;
    private ServiceErrorDetail serviceErrorDetail;
    private ErrorLogLevel errorLogLevel;
    private ExceptionDto exception;

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public ServiceErrorDetail getErrorDetail() {
        return serviceErrorDetail;
    }

    public void setErrorDetail(ServiceErrorDetail serviceErrorDetail) {
        this.serviceErrorDetail = serviceErrorDetail;
    }

    public ErrorLogLevel getErrorLogLevel() {
        return errorLogLevel;
    }

    public void setErrorLogLevel(ErrorLogLevel errorLogLevel) {
        this.errorLogLevel = errorLogLevel;
    }

    public ExceptionDto getException() {
        return exception;
    }

    public void setException(ExceptionDto exception) {
        this.exception = exception;
    }
}
