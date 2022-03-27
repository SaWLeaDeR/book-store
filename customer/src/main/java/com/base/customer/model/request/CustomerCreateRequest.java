package com.base.customer.model.request;

import com.base.model.request.GenericRequest;
import lombok.Data;

@Data
public class CustomerCreateRequest extends GenericRequest {

    private String name;
    private String surname;
}
