package com.base.customer.service.impl;

import com.base.customer.builder.CustomerDataBuilder;
import com.base.customer.model.dto.CustomerDto;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.customer.service.delegate.CustomerDelegateService;
import com.base.customer.validator.service.CustomerValidationService;
import com.base.model.dto.ListDto;
import com.base.model.response.ServiceResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {
    @Mock
    CustomerValidationService customerValidationService;
    @Mock
    CustomerDelegateService customerDelegateService;
    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer() throws Exception {
        final CustomerCreateRequest customerCreateRequest = CustomerDataBuilder.createCustomerCreateRequest();
        when(customerValidationService.validateOperation(any(), any())).thenReturn(ServiceResponse.of(customerCreateRequest));
        when(customerDelegateService.saveCustomer(customerCreateRequest)).thenReturn(ServiceResponse.of(CustomerDataBuilder.getCustomerDto()));

        ServiceResponse<CustomerDto> result = customerServiceImpl.saveCustomer(customerCreateRequest);
        assertNotNull(result);
        assertNotNull(result.getData().get());
        assertTrue(result.isSuccessful());
        assertEquals(CustomerDataBuilder.NAME, result.getData().get().getName());

        verify(customerValidationService, times(1)).validateOperation(any(), any());
        verify(customerDelegateService, times(1)).saveCustomer(customerCreateRequest);
        verifyNoMoreInteractions(customerValidationService);
        verifyNoMoreInteractions(customerDelegateService);
    }

    @Test
    public void testGetCustomer() throws Exception {
        final CustomerGetRequest customerGetRequest = CustomerDataBuilder.createCustomerGetRequest();
        when(customerValidationService.validateOperation(any(), any())).thenReturn(ServiceResponse.of(customerGetRequest));
        when(customerDelegateService.getCustomer(any())).thenReturn(ServiceResponse.of(new ListDto<>(0,
            Collections.singletonList(CustomerDataBuilder.getCustomerDto()))));

        ServiceResponse<ListDto<CustomerDto>> result = customerServiceImpl.getCustomer(customerGetRequest);
        assertNotNull(result);
        assertNotNull(result.getData().get());
        assertTrue(result.isSuccessful());
        assertEquals(CustomerDataBuilder.NAME, result.getData().get().getListData().get(0).getName());

        verify(customerValidationService, times(1)).validateOperation(any(), any());
        verify(customerDelegateService, times(1)).getCustomer(customerGetRequest);
        verifyNoMoreInteractions(customerValidationService);
        verifyNoMoreInteractions(customerDelegateService);
    }
}
