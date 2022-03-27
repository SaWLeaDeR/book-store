package com.base.customer.dao;

import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.request.CustomerGetRequest;
import org.springframework.data.domain.Page;

public interface CustomerDao {

    CustomerDocument save(CustomerDocument user);

    Page<CustomerDocument> getCustomers(CustomerGetRequest request);
}
