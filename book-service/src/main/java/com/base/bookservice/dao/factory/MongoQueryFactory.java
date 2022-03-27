package com.base.bookservice.dao.factory;

import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface MongoQueryFactory {

    Query getSearchQuery(BookGetRequest request);

    Query getIdQuery(String id);

    Update getUpdateQuery(BookUpdateDelegateRequest request);
}
