package com.base.customer.model.request;

import com.base.model.request.GenericPagingRequest;
import lombok.Data;

@Data
public class CustomerGetRequest extends GenericPagingRequest {

    private String name;
    private String surname;
}
