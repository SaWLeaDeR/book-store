package com.base.error.model;

import com.base.error.model.dto.ErrorLogLevel;

import java.util.Date;

public class ServiceError extends GenericErrorDto {

    private static final long serialVersionUID = -2062715849224561778L;

    private ServiceErrorDetail serviceErrorDetail;
    private ErrorEnum errorType;
    private Throwable exception;
    private ErrorLogLevel errorLogLevel;

    public ServiceError(ServiceErrorDetail serviceErrorDetail, ErrorEnum errorEnum) {
        setCreateDate(new Date());
        setServiceErrorDetail(serviceErrorDetail);
        setErrorType(errorEnum);
    }

    public ServiceError(Throwable throwable) {
        setCreateDate(new Date());
        setException(throwable);
    }

    public ServiceErrorDetail getServiceErrorDetail() {
        return serviceErrorDetail;
    }

    public void setServiceErrorDetail(ServiceErrorDetail serviceErrorDetail) {
        this.serviceErrorDetail = serviceErrorDetail;
    }

    public ErrorEnum getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorEnum errorType) {
        this.errorType = errorType;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public ServiceError atErrorLogLevel(ErrorLogLevel errorLogLevel) {
        setErrorLogLevel(errorLogLevel);
        return this;
    }

    public ErrorLogLevel getErrorLogLevel() {
        return errorLogLevel;
    }

    public void setErrorLogLevel(ErrorLogLevel errorLogLevel) {
        this.errorLogLevel = errorLogLevel;
    }
}
