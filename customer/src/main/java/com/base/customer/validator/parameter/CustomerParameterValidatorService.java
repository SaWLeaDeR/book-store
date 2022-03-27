package com.base.customer.validator.parameter;

import com.base.customer.validator.model.dto.CustomerParameterValidatorInput;
import com.base.error.model.ServiceError;

import java.util.Optional;

public interface CustomerParameterValidatorService {

    Optional<ServiceError> validate(CustomerParameterValidatorInput validatorInput, Boolean isEmptyValid);
}
