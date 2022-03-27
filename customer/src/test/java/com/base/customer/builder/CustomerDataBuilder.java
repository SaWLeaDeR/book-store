package com.base.customer.builder;

import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.model.request.GenericRequest;
import com.base.model.response.ServiceResponse;

public class CustomerDataBuilder {

    public static final String ID = "6240af2482b99a349866ac47";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static CustomerDto getCustomerDto() {
        CustomerDto target = new CustomerDto();
        target.setId(ID);
        target.setName(NAME);
        target.setSurname(SURNAME);
        return target;
    }

    public static CustomerCreateRequest createCustomerCreateRequest() {
        CustomerCreateRequest target = new CustomerCreateRequest();
        target.setName(NAME);
        target.setSurname(SURNAME);
        return target;
    }

    public static CustomerGetRequest createCustomerGetRequest() {
        CustomerGetRequest target = new CustomerGetRequest();
        target.setName(NAME);
        target.setSurname(SURNAME);
        return target;
    }

    public static CustomerDocument createCustomerDocument() {
        CustomerDocument target = new CustomerDocument();
        target.setId(ID);
        target.setName(NAME);
        target.setSurname(SURNAME);
        return target;
    }
}
