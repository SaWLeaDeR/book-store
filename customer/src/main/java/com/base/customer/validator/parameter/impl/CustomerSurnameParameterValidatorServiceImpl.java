package com.base.customer.validator.parameter.impl;

import com.base.customer.validator.model.dto.CustomerParameterValidatorInput;
import com.base.customer.validator.parameter.CustomerParameterValidatorService;
import com.base.error.model.ServiceError;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class CustomerSurnameParameterValidatorServiceImpl implements CustomerParameterValidatorService {

    private final List<Function<String, Optional<ServiceError>>> surnameParameterValidations;

    public CustomerSurnameParameterValidatorServiceImpl(
        @Qualifier("surnameParameterValidations") List<Function<String, Optional<ServiceError>>> surnameParameterValidations) {
        this.surnameParameterValidations = surnameParameterValidations;
    }

    @Override
    public Optional<ServiceError> validate(
        CustomerParameterValidatorInput validatorInput, Boolean isEmptyValid) {
        if (Boolean.TRUE.equals(isEmptyValid) && null == validatorInput.getSurname()) {
            return Optional.empty();
        }
        return surnameParameterValidations.stream()
            .map(validation -> validation.apply(validatorInput.getSurname()))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }
}
