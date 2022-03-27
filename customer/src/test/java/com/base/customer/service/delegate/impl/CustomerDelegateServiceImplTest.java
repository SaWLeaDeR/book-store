package com.base.customer.service.delegate.impl;

import com.base.customer.builder.CustomerDataBuilder;
import com.base.customer.dao.CustomerDao;
import com.base.customer.model.converter.CustomerCreateRequestConverter;
import com.base.customer.model.converter.impl.CustomerDtoConverter;
import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerDelegateServiceImpl.class})
public class CustomerDelegateServiceImplTest {
    @Autowired
    private CustomerDelegateServiceImpl customerDelegateServiceImpl;
    @MockBean
    private CustomerDao customerDao;
    @SpyBean
    private CustomerCreateRequestConverter customerCreateRequestConverter;
    @SpyBean
    private CustomerDtoConverter customerDtoConverter;

    //@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer() throws Exception {
        final CustomerDocument customerDocument = CustomerDataBuilder.createCustomerDocument();
        when(customerDao.save(any())).thenReturn(customerDocument);

        final CustomerCreateRequest customerCreateRequest = CustomerDataBuilder.createCustomerCreateRequest();
        ServiceResponse<CustomerDto> result = customerDelegateServiceImpl.saveCustomer(customerCreateRequest);

        assertNotNull(result);
        assertNotNull(result.getData().get());
        assertTrue(result.isSuccessful());
        assertEquals(CustomerDataBuilder.NAME, result.getData().get().getName());

        verify(customerDao, times(1)).save(any());
        verify(customerCreateRequestConverter, times(1)).convert(customerCreateRequest);
        verify(customerDtoConverter, times(1)).convert(customerDocument);
        verifyNoMoreInteractions(customerDao);
        verifyNoMoreInteractions(customerCreateRequestConverter);
        verifyNoMoreInteractions(customerDtoConverter);
    }

    @Test
    public void testGetCustomer() throws Exception {
        final Page<CustomerDocument> page = new PageImpl<>(Collections.singletonList(CustomerDataBuilder.createCustomerDocument()));
        when(customerDao.getCustomers(any())).thenReturn(page);

        ServiceResponse<ListDto<CustomerDto>> result = customerDelegateServiceImpl.getCustomer(CustomerDataBuilder.createCustomerGetRequest());
        assertNotNull(result);
        assertNotNull(result.getData().get());
        assertTrue(result.isSuccessful());
        assertEquals(CustomerDataBuilder.NAME, result.getData().get().getListData().get(0).getName());

        verify(customerDao, times(1)).getCustomers(any());
        verify(customerCreateRequestConverter, times(0)).convert(anyList());
        verify(customerDtoConverter, times(1)).convert(anyList());
    }
}
