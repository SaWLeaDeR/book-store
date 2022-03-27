package com.base.customer.dao.impl;

import com.base.customer.dao.CustomerDao;
import com.base.customer.dao.factory.MongoQueryFactory;
import com.base.customer.model.document.CustomerDocument;
import com.base.customer.model.request.CustomerGetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private static final String COLLECTION_NAME = "customer";
    private final MongoQueryFactory mongoQueryFactory;
    private final MongoTemplate mongoTemplate;

    public CustomerDaoImpl(MongoQueryFactory mongoQueryFactory,
                           MongoTemplate mongoTemplate) {
        this.mongoQueryFactory = mongoQueryFactory;
        this.mongoTemplate = mongoTemplate;
    }


    public CustomerDocument save(CustomerDocument user) {
        return mongoTemplate.save(user, COLLECTION_NAME);

    }

    @Override
    public Page<CustomerDocument> getCustomers(CustomerGetRequest request) {
        final PageRequest pageRequest = mongoQueryFactory.getPageRequest(request.getPage(), request.getLimit());
        final Query getQuery = mongoQueryFactory.getSearchQuery(request);
        return PageableExecutionUtils.getPage(mongoTemplate.find(getQuery, CustomerDocument.class, COLLECTION_NAME),
            pageRequest, () ->
                mongoTemplate.count(getQuery.skip(0).limit(0), CustomerDocument.class, COLLECTION_NAME));
    }

}
