package com.base.bookstore.domain.service;

import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import com.base.bookstore.delegate.customer.model.dto.CustomerDto;
import com.base.bookstore.domain.model.request.CustomerCreateRequest;
import com.base.bookstore.domain.model.request.CustomerGetRequest;

public interface CustomerService {

    ServiceResponse<CustomerDto> saveCustomer(CustomerCreateRequest request);

    ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request);
}
