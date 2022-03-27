package com.base.bookservice.validation.parameter.impl;

import com.base.bookservice.validation.model.dto.BookParameterValidatorInput;
import com.base.bookservice.validation.parameter.BookParameterValidatorService;
import com.base.error.model.ServiceError;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class BookIdParameterValidatorServiceImpl implements BookParameterValidatorService {

    private final List<Function<String, Optional<ServiceError>>> idParameterValidations;

    public BookIdParameterValidatorServiceImpl(
        @Qualifier("idParameterValidations") List<Function<String, Optional<ServiceError>>> idParameterValidations) {
        this.idParameterValidations = idParameterValidations;
    }

    @Override
    public Optional<ServiceError> validate(BookParameterValidatorInput parameterValidatorInput, boolean isEmptyValid) {
        if (Boolean.TRUE.equals(isEmptyValid) || null == parameterValidatorInput.getId()) {
            return Optional.empty();
        }

        return idParameterValidations.stream()
            .map(validation -> validation.apply(parameterValidatorInput.getId()))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }
}
