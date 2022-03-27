package com.base.bookservice.dao.factory.impl;

import com.base.bookservice.dao.factory.MongoQueryFactory;
import com.base.bookservice.model.document.property.BookDocumentProperty;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.BookUpdateRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoQueryFactoryImpl implements MongoQueryFactory {

    @Override
    public Query getSearchQuery(BookGetRequest request) {
        Query query = new Query();
        getField(request.getName())
            .ifPresent(result ->
                query.addCriteria(Criteria.where(BookDocumentProperty.NAME).is(request.getName())));
        getField(request.getSeller())
            .ifPresent(result ->
                query.addCriteria(Criteria.where(BookDocumentProperty.SELLER).is(request.getSeller())));
        return query;
    }

    @Override
    public Query getIdQuery(String id) {
        Query query = new Query();
        getField(id)
            .ifPresent(result ->
                query.addCriteria(Criteria.where(BookDocumentProperty.ID).is(id)));
        return query;
    }

    @Override
    public Update getUpdateQuery(BookUpdateDelegateRequest request) {
        Update update = new Update();
        getField(request.getName())
            .ifPresent(result -> update.set(BookDocumentProperty.NAME, request.getName()));
        getField(request.getSeller())
            .ifPresent(result -> update.set(BookDocumentProperty.SELLER, request.getSeller()));
        getField(request.getStock())
            .ifPresent(result -> update.set(BookDocumentProperty.STOCK, request.getStock()));
        return update;
    }

    private Optional<String> getField(String variable) {
        if (null == variable) {
            return Optional.empty();
        }
        return Optional.of(variable);
    }

    private Optional<Integer> getField(Integer variable) {
        if (null == variable) {
            return Optional.empty();
        }
        return Optional.of(variable);
    }
}
