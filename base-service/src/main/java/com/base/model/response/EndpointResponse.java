package com.base.model.response;

import com.base.error.model.ErrorSignDto;
import com.base.model.dto.GenericDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class EndpointResponse<T extends GenericDto> implements Serializable {
    private static final long serialVersionUID = -2038602651341457790L;

    @JsonIgnore
    private HttpStatus httpStatus = HttpStatus.OK;

    private String elapsedTime;
    private T data;
    private ErrorSignDto errors;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorSignDto getErrors() {
        return errors;
    }

    public void setErrors(ErrorSignDto errors) {
        this.errors = errors;
    }
}
