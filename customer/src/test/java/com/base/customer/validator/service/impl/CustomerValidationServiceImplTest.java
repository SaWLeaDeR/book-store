package com.base.customer.validator.service.impl;

import com.base.customer.builder.CustomerDataBuilder;
import com.base.customer.model.error.CustomerError;
import com.base.customer.model.request.CustomerGetRequest;
import com.base.customer.validator.CustomerRequestValidator;
import com.base.customer.validator.impl.CustomerCreateRequestValidatorImpl;
import com.base.customer.validator.impl.CustomerGetRequestValidatorImpl;
import com.base.customer.validator.model.enumeration.CustomerRequestType;
import com.base.error.ErrorMarker;
import com.base.error.model.ServiceErrorDetail;
import com.base.model.response.ServiceResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerValidationServiceImplTest {
    @Mock
    private Map<CustomerRequestType, CustomerRequestValidator> customerRequestValidatorMap;
    @Mock
    private CustomerCreateRequestValidatorImpl customerCreateRequestValidator;
    @Mock
    private CustomerGetRequestValidatorImpl customerGetRequestValidator;
    @Mock
    private ErrorMarker errorMarker;
    @InjectMocks
    CustomerValidationServiceImpl customerValidationServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetValidateOperation() throws Exception {
        when(customerRequestValidatorMap.get(any())).thenReturn(customerGetRequestValidator);
        when(errorMarker.getErrorToken(any(), any(), any())).thenReturn(new ServiceErrorDetail("001", CustomerError.TOP_ERROR_DOMAIN,
            CustomerError.VALIDATOR_NOT_FOUND));
        when(customerGetRequestValidator.validate(any())).thenReturn(Optional.empty());

        ServiceResponse<CustomerGetRequest> result = customerValidationServiceImpl.validateOperation(CustomerRequestType.GET,
            CustomerDataBuilder.createCustomerGetRequest());
        assertNotNull(result);
        assertNotNull(result.getData().get());
        assertTrue(result.isSuccessful());
        assertEquals(CustomerDataBuilder.NAME, result.getData().get().getName());

        verify(customerRequestValidatorMap, times(1)).get(any());
        verify(errorMarker, times(0)).getErrorToken(any(), any(), any());
        verify(customerGetRequestValidator, times(1)).validate(any());
        verify(customerCreateRequestValidator, times(0)).validate(any());
        verifyNoMoreInteractions(customerRequestValidatorMap);
        verifyNoMoreInteractions(errorMarker);
        verifyNoMoreInteractions(customerGetRequestValidator);
        verifyNoMoreInteractions(customerCreateRequestValidator);
    }

    @Test
    public void testCreateValidateOperation() throws Exception {
        when(customerRequestValidatorMap.get(any())).thenReturn(customerCreateRequestValidator);
        when(errorMarker.getErrorToken(any(), any(), any())).thenReturn(new ServiceErrorDetail("code", "topDomain", "message"));

        ServiceResponse<CustomerGetRequest> result = customerValidationServiceImpl.validateOperation(CustomerRequestType.GET,
            CustomerDataBuilder.createCustomerGetRequest());
        assertNotNull(result);
        assertNotNull(result.getData().get());
        assertTrue(result.isSuccessful());
        assertEquals(CustomerDataBuilder.NAME, result.getData().get().getName());

        verify(customerRequestValidatorMap, times(1)).get(any());
        verify(errorMarker, times(0)).getErrorToken(any(), any(), any());
        verify(customerCreateRequestValidator, times(1)).validate(any());
        verify(customerGetRequestValidator, times(0)).validate(any());
        verifyNoMoreInteractions(customerRequestValidatorMap);
        verifyNoMoreInteractions(errorMarker);
        verifyNoMoreInteractions(customerCreateRequestValidator);
        verifyNoMoreInteractions(customerGetRequestValidator);
    }
}
