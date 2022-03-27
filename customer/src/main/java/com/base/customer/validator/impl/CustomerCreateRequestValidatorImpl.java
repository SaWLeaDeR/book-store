package com.base.customer.validator.impl;

import com.base.customer.model.error.CustomerError;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.validator.CustomerRequestValidator;
import com.base.customer.validator.model.dto.CustomerParameterValidatorInput;
import com.base.customer.validator.model.dto.CustomerValidatorInput;
import com.base.customer.validator.model.enumeration.CustomerRequestType;
import com.base.customer.validator.parameter.CustomerParameterValidatorService;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.model.request.GenericRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerCreateRequestValidatorImpl implements CustomerRequestValidator {

    private final List<CustomerParameterValidatorService> customerParameterValidators;
    private final ErrorMarker errorMarker;

    public CustomerCreateRequestValidatorImpl(List<CustomerParameterValidatorService> customerParameterValidators, ErrorMarker errorMarker) {
        this.customerParameterValidators = customerParameterValidators;
        this.errorMarker = errorMarker;
    }

    @Override
    public <T extends GenericRequest> Optional<ServiceError> validate(
        CustomerValidatorInput<T> customerValidatorInput) {

        final CustomerCreateRequest request = (CustomerCreateRequest) customerValidatorInput.getRequest();

        if (null == request) {
            return Optional.of(ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(CustomerError.TOP_ERROR_DOMAIN, CustomerError.EMPTY_REQUEST))
                .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED));
        }

        final CustomerParameterValidatorInput validatorInput = CustomerParameterValidatorInput.builder()
            .name(request.getName())
            .surname(request.getSurname())
            .build();

        return customerParameterValidators.stream()
            .map(validator -> validator.validate(validatorInput, false))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }

    @Override
    public CustomerRequestType getValidatorType() {
        return CustomerRequestType.CREATE;
    }
}
