package com.base.customer.service;

import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;

public interface CustomerService {
    ServiceResponse<CustomerDto> saveCustomer(CustomerCreateRequest request);

    ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request);
}
