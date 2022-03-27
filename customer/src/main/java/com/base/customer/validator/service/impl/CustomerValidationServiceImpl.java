package com.base.customer.validator.service.impl;

import com.base.customer.model.error.CustomerError;
import com.base.customer.validator.CustomerRequestValidator;
import com.base.customer.validator.model.dto.CustomerValidatorInput;
import com.base.customer.validator.model.enumeration.CustomerRequestType;
import com.base.customer.validator.service.CustomerValidationService;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.model.request.GenericRequest;
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    private final Map<CustomerRequestType, CustomerRequestValidator> customerRequestValidatorMap;
    private final ErrorMarker errorMarker;

    public CustomerValidationServiceImpl(
        Map<CustomerRequestType, CustomerRequestValidator> customerRequestValidatorMap,
        ErrorMarker errorMarker) {
        this.customerRequestValidatorMap = customerRequestValidatorMap;
        this.errorMarker = errorMarker;
    }


    @Override
    public <T extends GenericRequest> ServiceResponse<T> validateOperation(
        CustomerRequestType type, T request) {
        final CustomerRequestValidator customerRequestValidator = customerRequestValidatorMap.get(type);

        if (customerRequestValidator == null) {
            return ServiceResponse.ofError(
                getError(CustomerError.VALIDATOR_NOT_FOUND));
        }

        final Optional<ServiceError> validationResult = customerRequestValidator
            .validate(new CustomerValidatorInput<>(request));

        return validationResult.<ServiceResponse<T>>map(ServiceResponse::ofError).orElseGet(() -> ServiceResponse.of(request));
    }

    private ServiceError getError(String errorMessage) {
        return ErrorFactory.fromErrorToken(
                errorMarker.getErrorToken(CustomerError.TOP_ERROR_DOMAIN, errorMessage))
            .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED);
    }
}
