package com.base.customer.service.delegate.impl;

import com.base.aspect.annotation.InternalLogger;
import com.base.converter.GenericConverter;
import com.base.customer.dao.CustomerDao;
import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.customer.service.delegate.CustomerDelegateService;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDelegateServiceImpl implements CustomerDelegateService {

    private final CustomerDao customerDao;
    private final GenericConverter<CustomerCreateRequest, CustomerDocument> customerCreateRequestConverter;
    private final GenericConverter<CustomerDocument, CustomerDto> customerDtoConverter;

    public CustomerDelegateServiceImpl(CustomerDao customerDao,
                                       GenericConverter<CustomerCreateRequest, CustomerDocument> customerCreateRequestConverter,
                                       GenericConverter<CustomerDocument, CustomerDto> customerDtoConverter) {
        this.customerDao = customerDao;
        this.customerCreateRequestConverter = customerCreateRequestConverter;
        this.customerDtoConverter = customerDtoConverter;
    }

    @Override
    @InternalLogger(name = "saveCustomer")
    public ServiceResponse<CustomerDto> saveCustomer(CustomerCreateRequest request) {
        final CustomerDocument document = customerCreateRequestConverter.convert(request);
        final CustomerDocument customerDocument = customerDao.save(document);
        final CustomerDto customer = customerDtoConverter.convert(customerDocument);
        return ServiceResponse.of(customer);
    }

    @Override
    @InternalLogger(name = "getCustomer")
    public ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request) {
        final Page<CustomerDocument> customerDocuments = customerDao.getCustomers(request);
        final List<CustomerDto> customers = customerDtoConverter.convert(customerDocuments.getContent());
        return ServiceResponse.of(new ListDto<>(customerDocuments.getTotalElements(), customers));
    }
}
