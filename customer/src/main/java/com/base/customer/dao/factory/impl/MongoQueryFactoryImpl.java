package com.base.customer.dao.factory.impl;

import com.base.customer.dao.factory.MongoQueryFactory;
import com.base.customer.model.document.property.CustomerProperty;
import com.base.customer.model.request.CustomerCreateRequest;
import com.base.customer.model.request.CustomerGetRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoQueryFactoryImpl implements MongoQueryFactory {

    @Override
    public Query getSearchQuery(CustomerGetRequest request) {
        Query query = new Query();
        getField(request.getName())
            .ifPresent(result ->
                query.addCriteria(Criteria.where(CustomerProperty.NAME).is(request.getName())));
        getField(request.getSurname())
            .ifPresent(result ->
                query.addCriteria(Criteria.where(CustomerProperty.SURNAME).is(request.getSurname())));
        return query;
    }

    @Override
    public PageRequest getPageRequest(Integer page, Integer limit) {
        return PageRequest.of(page, limit);
    }

    private Optional<String> getField(String variable) {
        if (null == variable) {
            return Optional.empty();
        }
        return Optional.of(variable);
    }
}
