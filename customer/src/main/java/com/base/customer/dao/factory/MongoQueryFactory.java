package com.base.customer.dao.factory;

import com.base.customer.model.request.CustomerGetRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

public interface MongoQueryFactory {

    Query getSearchQuery(CustomerGetRequest request);

    PageRequest getPageRequest(Integer page, Integer limit);
}
