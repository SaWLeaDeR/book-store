package com.base.customer.validator.service;

import com.base.customer.validator.model.enumeration.CustomerRequestType;
import com.base.model.request.GenericRequest;
import com.base.model.response.ServiceResponse;

public interface CustomerValidationService {

    <T extends GenericRequest> ServiceResponse<T> validateOperation(CustomerRequestType type, T request);
}
