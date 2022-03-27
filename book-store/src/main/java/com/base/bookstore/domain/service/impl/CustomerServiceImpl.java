package com.base.bookstore.domain.service.impl;

import com.base.bookstore.delegate.customer.service.CustomerDelegateService;
import com.base.bookstore.domain.config.CustomerConfiguration;
import com.base.bookstore.domain.model.request.CustomerCreateRequest;
import com.base.bookstore.domain.model.request.CustomerGetRequest;
import com.base.bookstore.domain.service.CustomerService;
import com.base.model.dto.ListDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import com.base.bookstore.delegate.customer.model.dto.CustomerDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDelegateService customerDelegateService;


    public CustomerServiceImpl(CustomerDelegateService customerDelegateService) {
        this.customerDelegateService = customerDelegateService;
    }

    @Override
    public ServiceResponse<CustomerDto> saveCustomer(
        CustomerCreateRequest request) {
        return customerDelegateService.saveCustomer(request);
    }

    @Override
    public ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request) {
        return customerDelegateService.getCustomer(request);
    }
}
