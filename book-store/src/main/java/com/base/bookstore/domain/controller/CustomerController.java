package com.base.bookstore.domain.controller;

import com.base.bookstore.domain.controller.endpoint.CustomerEndpoint;
import com.base.bookstore.domain.model.request.CustomerCreateRequest;
import com.base.bookstore.domain.model.request.CustomerGetRequest;
import com.base.bookstore.domain.service.CustomerService;
import com.base.controller.GenericController;
import com.base.controller.Runner;
import com.base.model.dto.ListDto;
import com.base.model.response.EndpointResponse;
import com.base.bookstore.delegate.customer.model.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController extends GenericController<CustomerService> {

    public CustomerController(Runner<CustomerService, CustomerDto> runner,
                              CustomerService service) {
        setRunner(runner);
        setServiceProvider(service);
    }

    @PostMapping(value = CustomerEndpoint.CUSTOMER)
    public ResponseEntity<EndpointResponse<CustomerDto>> saveCustomer(@RequestBody CustomerCreateRequest request) {
        return run(service -> service.saveCustomer(request));
    }

    @GetMapping(value = CustomerEndpoint.CUSTOMER)
    public ResponseEntity<EndpointResponse<ListDto<CustomerDto>>> getCustomer(@ModelAttribute CustomerGetRequest request) {
        return run(service -> service.getCustomer(request));
    }
}
