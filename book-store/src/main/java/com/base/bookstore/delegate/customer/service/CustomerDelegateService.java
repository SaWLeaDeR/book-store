package com.base.bookstore.delegate.customer.service;

import com.base.bookstore.delegate.customer.model.dto.CustomerDto;
import com.base.bookstore.domain.model.request.CustomerCreateRequest;
import com.base.bookstore.domain.model.request.CustomerGetRequest;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;

public interface CustomerDelegateService {

    ServiceResponse<CustomerDto> saveCustomer(CustomerCreateRequest request);

    ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request);
}
