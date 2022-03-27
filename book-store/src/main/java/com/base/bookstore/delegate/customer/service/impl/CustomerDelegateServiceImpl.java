package com.base.bookstore.delegate.customer.service.impl;

import com.base.bookstore.delegate.customer.model.dto.CustomerDto;
import com.base.bookstore.delegate.customer.service.CustomerDelegateService;
import com.base.bookstore.domain.config.CustomerConfiguration;
import com.base.bookstore.domain.model.request.CustomerCreateRequest;
import com.base.bookstore.domain.model.request.CustomerGetRequest;
import com.base.error.factory.ServiceResponseLocator;
import com.base.model.dto.ListDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerDelegateServiceImpl implements CustomerDelegateService {

    private final RestTemplate restTemplate;
    private final CustomerConfiguration customerConfiguration;
    private final ServiceResponseLocator serviceResponseLocator;

    public CustomerDelegateServiceImpl(RestTemplate restTemplate,
                                       CustomerConfiguration customerConfiguration,
                                       ServiceResponseLocator serviceResponseLocator) {
        this.restTemplate = restTemplate;
        this.customerConfiguration = customerConfiguration;
        this.serviceResponseLocator = serviceResponseLocator;
    }

    @Override
    public ServiceResponse<CustomerDto> saveCustomer(
        CustomerCreateRequest request) {
        try {
            final String url = customerConfiguration.customerProperties().getUrl() + customerConfiguration.customerProperties().getSave();
            HttpEntity<CustomerCreateRequest> requests = new HttpEntity<>(request, null);
            final EndpointResponse<CustomerDto> response = this.restTemplate.exchange(url, HttpMethod.POST, requests,
                new ParameterizedTypeReference<EndpointResponse<CustomerDto>>() {
                }).getBody();
            return serviceResponseLocator.factory(response, "customer-service-error");
        } catch (HttpClientErrorException e) {

            return ServiceResponse.empty();
        }
    }

    @Override
    public ServiceResponse<ListDto<CustomerDto>> getCustomer(CustomerGetRequest request) {
        final String url = getCustomerUrl(request);
        HttpEntity<CustomerGetRequest> requests = new HttpEntity<>(request, null);
        final EndpointResponse<ListDto<CustomerDto>> response = this.restTemplate.exchange(url, HttpMethod.GET, requests,
            new ParameterizedTypeReference<EndpointResponse<ListDto<CustomerDto>>>() {
            }).getBody();

        return serviceResponseLocator.factory(response, "customer-service-error");
    }

    private String getCustomerUrl(CustomerGetRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(customerConfiguration.customerProperties().getUrl());
        builder.append(customerConfiguration.customerProperties().getGet());

        if (null != request.getLimit() || null != request.getSurname()
            || null != request.getPage() || null != request.getName()) {
            builder.append("?");
        }

        if (null != request.getName()) {
            builder.append("name=");
            builder.append(request.getName());
        }

        if (null != request.getSurname()) {
            builder.append("&surname=");
            builder.append(request.getSurname());
        }

        if (null != request.getPage()) {
            builder.append("&page=");
            builder.append(request.getPage());
        }

        if (null != request.getLimit()) {
            builder.append("&limit=");
            builder.append(request.getLimit());
        }

        return builder.toString();
    }
}
