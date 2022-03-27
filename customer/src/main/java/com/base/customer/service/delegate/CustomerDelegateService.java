package com.base.customer.service.delegate;

import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;

public interface CustomerDelegateService {

    ServiceResponse<CustomerDto> saveCustomer(CustomerCreateRequest request);

    ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request);
}
