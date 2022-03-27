package com.base.customer.service.impl;

import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.customer.service.CustomerService;
import com.base.customer.service.delegate.CustomerDelegateService;
import com.base.customer.validator.model.enumeration.CustomerRequestType;
import com.base.customer.validator.service.CustomerValidationService;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerValidationService customerValidationService;
    private final CustomerDelegateService customerDelegateService;

    public CustomerServiceImpl(CustomerValidationService customerValidationService,
                               CustomerDelegateService customerDelegateService) {
        this.customerValidationService = customerValidationService;
        this.customerDelegateService = customerDelegateService;
    }

    @Override
    public ServiceResponse<CustomerDto> saveCustomer(
        CustomerCreateRequest request) {

        return customerValidationService.validateOperation(CustomerRequestType.CREATE, request)
            .ifSuccessfullyPresent(success -> customerDelegateService.saveCustomer(success.getData().get()));
    }

    @Override
    public ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request) {
        return customerValidationService.validateOperation(CustomerRequestType.GET, request)
            .ifSuccessfullyPresent(success -> customerDelegateService.getCustomer(success.getData().get()));
    }
}
