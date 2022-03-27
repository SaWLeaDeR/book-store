package com.base.bookservice.dao.impl;

import com.base.bookservice.dao.BookDao;
import com.base.bookservice.dao.factory.MongoQueryFactory;
import com.base.bookservice.model.document.BookDocument;
import com.base.bookservice.model.error.BookError;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.model.response.ServiceResponse;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String COLLECTION_NAME = "book";
    private final MongoQueryFactory mongoQueryFactory;
    private final MongoTemplate mongoTemplate;
    private final ErrorMarker errorMarker;

    public BookDaoImpl(MongoQueryFactory mongoQueryFactory,
                       MongoTemplate mongoTemplate, ErrorMarker errorMarker) {
        this.mongoQueryFactory = mongoQueryFactory;
        this.mongoTemplate = mongoTemplate;
        this.errorMarker = errorMarker;
    }


    @Override
    public List<BookDocument> getBooks(BookGetRequest request) {
        final Query searchQuery = mongoQueryFactory.getSearchQuery(request);
        return mongoTemplate.find(searchQuery, BookDocument.class, COLLECTION_NAME);
    }

    @Override
    public BookDocument getBook(String id) {
        return mongoTemplate.findById(id, BookDocument.class, COLLECTION_NAME);
    }

    @Override
    public BookDocument save(BookDocument book) {
        return mongoTemplate.save(book, COLLECTION_NAME);
    }

    @Override
    public ServiceResponse<BookDocument> update(BookUpdateDelegateRequest request) {
        final Query findQuery = mongoQueryFactory.getIdQuery(request.getId());
        final Update updateQuery = mongoQueryFactory.getUpdateQuery(request);
        final UpdateResult updateResult = mongoTemplate.updateFirst(findQuery, updateQuery, BookDocument.class, COLLECTION_NAME);

        return updateResult.getMatchedCount() > 0 ? ServiceResponse.of(mongoTemplate.findById(request.getId(), BookDocument.class, COLLECTION_NAME)) :
            ServiceResponse.ofError(getError());
    }

    private ServiceError getError() {
        return ErrorFactory.fromErrorToken(
                errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, BookError.NOT_FOUND))
            .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED);
    }
}
