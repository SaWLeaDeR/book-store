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
public class CustomerNameParameterValidatorServiceImpl implements CustomerParameterValidatorService {

    private final List<Function<String, Optional<ServiceError>>> nameParameterValidations;

    public CustomerNameParameterValidatorServiceImpl(
        @Qualifier("nameParameterValidations") List<Function<String, Optional<ServiceError>>> nameParameterValidations) {
        this.nameParameterValidations = nameParameterValidations;
    }

    @Override
    public Optional<ServiceError> validate(
        CustomerParameterValidatorInput validatorInput, Boolean isEmptyValid) {
        if (Boolean.TRUE.equals(isEmptyValid) && null == validatorInput.getName()) {
            return Optional.empty();
        }
        return nameParameterValidations.stream()
            .map(validation -> validation.apply(validatorInput.getName()))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }
}
