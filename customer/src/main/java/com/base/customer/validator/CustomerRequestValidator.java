package com.base.customer.validator;

import com.base.customer.validator.model.dto.CustomerValidatorInput;
import com.base.customer.validator.model.enumeration.CustomerRequestType;
import com.base.error.model.ServiceError;
import com.base.model.request.GenericRequest;

import java.util.Optional;

public interface CustomerRequestValidator {

    <T extends GenericRequest> Optional<ServiceError> validate(CustomerValidatorInput<T> customerValidatorInput);

    CustomerRequestType getValidatorType();
}
