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
public class BookNameParameterValidatorServiceImpl implements BookParameterValidatorService {

    private final List<Function<String, Optional<ServiceError>>> nameParameterValidations;

    public BookNameParameterValidatorServiceImpl(
        @Qualifier("nameParameterValidations") List<Function<String, Optional<ServiceError>>> nameParameterValidations) {
        this.nameParameterValidations = nameParameterValidations;
    }

    @Override
    public Optional<ServiceError> validate(BookParameterValidatorInput parameterValidatorInput, boolean isEmptyValid) {
        if (Boolean.TRUE.equals(isEmptyValid) && null == parameterValidatorInput.getName()) {
            return Optional.empty();
        }

        return nameParameterValidations.stream()
            .map(validation -> validation.apply(parameterValidatorInput.getName()))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }
}
