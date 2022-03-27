package com.base.customer.model.converter.impl;

import com.base.converter.GenericConverter;
import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter implements GenericConverter<CustomerDocument, CustomerDto> {

    @Override
    public CustomerDto convert(CustomerDocument source) {
        CustomerDto target = new CustomerDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        return target;
    }
}
