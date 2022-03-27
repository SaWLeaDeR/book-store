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
public class BookSellerParameterValidatorServiceImpl implements BookParameterValidatorService {

    private final List<Function<String, Optional<ServiceError>>> sellerParameterValidations;

    public BookSellerParameterValidatorServiceImpl(
        @Qualifier("sellerParameterValidations") List<Function<String, Optional<ServiceError>>> sellerParameterValidations) {
        this.sellerParameterValidations = sellerParameterValidations;
    }

    @Override
    public Optional<ServiceError> validate(BookParameterValidatorInput parameterValidatorInput, boolean isEmptyValid) {
        if (Boolean.TRUE.equals(isEmptyValid) && null == parameterValidatorInput.getSeller()) {
            return Optional.empty();
        }

        return sellerParameterValidations.stream()
            .map(validation -> validation.apply(parameterValidatorInput.getSeller()))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }
}
