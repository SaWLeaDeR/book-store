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
public class BookStockParameterValidatorServiceImpl implements BookParameterValidatorService {

    private final List<Function<Integer, Optional<ServiceError>>> stockParameterValidations;

    public BookStockParameterValidatorServiceImpl(
        @Qualifier("stockParameterValidations") List<Function<Integer, Optional<ServiceError>>> stockParameterValidations) {
        this.stockParameterValidations = stockParameterValidations;
    }

    @Override
    public Optional<ServiceError> validate(BookParameterValidatorInput parameterValidatorInput, boolean isEmptyValid) {
        if (Boolean.TRUE.equals(isEmptyValid) || null == parameterValidatorInput.getStock()) {
            return Optional.empty();
        }

        return stockParameterValidations.stream()
            .map(validation -> validation.apply(parameterValidatorInput.getStock()))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }
}
