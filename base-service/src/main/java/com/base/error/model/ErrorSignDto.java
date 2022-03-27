package com.base.error.model;

import com.base.error.model.dto.ErrorDto;

import java.util.HashSet;
import java.util.Set;

public class ErrorSignDto extends GenericErrorDto {

    private static final long serialVersionUID = 59331496093942287L;

    private Set<ErrorDto> errors = new HashSet<>();

    public ErrorSignDto() {
    }

    public ErrorSignDto(ErrorDto error) {
        getErrors().add(error);
    }

    public Set<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(Set<ErrorDto> errors) {
        this.errors = errors;
    }

    public void addError(ErrorDto error) {
        getErrors().add(error);
    }

    public void addErrors(Set<ErrorDto> errors) {
        getErrors().addAll(errors);
    }

}
