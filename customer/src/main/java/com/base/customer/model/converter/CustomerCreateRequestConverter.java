package com.base.customer.model.converter;

import com.base.converter.GenericConverter;
import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.request.CustomerCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreateRequestConverter implements GenericConverter<CustomerCreateRequest, CustomerDocument> {

    @Override
    public CustomerDocument convert(CustomerCreateRequest source) {
        CustomerDocument target = new CustomerDocument();
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        return target;
    }
}
